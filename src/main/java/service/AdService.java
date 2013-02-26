package service;

import exception.AppException;
import model.*;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

@Stateless
public class AdService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private EJBContext context;

    static final Logger log = Logger.getLogger(AdService.class);


    @RolesAllowed("REGISTERED")
    public Ad createAd(Ad ad) {
        em.persist(ad);
        return ad;
    }

    @RolesAllowed("REGISTERED")
    public Ad updateAd(Ad ad) {
        if (ad.getUser().getId() != null) {
            if (!ad.getUser().getId().equals(context.getCallerPrincipal().toString()))
                throw new AppException("Hacking attempt");
        }
        em.merge(ad);
        return ad;
    }

    public List<Ad> getAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        Root<Ad> adRoot = cq.from(Ad.class);
        Join<Ad, Picture> pictureJoin =
                adRoot.join(Ad_.pictureList, JoinType.LEFT); //All Ads, even if no match in picture
        cq.select(adRoot);
        Predicate predicate1 = cb.isNull(adRoot.get(Ad_.removed));
        Predicate predicate2 = cb.isNull(pictureJoin.get(Picture_.removed));
        cq.where(cb.and(predicate1,predicate2));
        cq.distinct(true); // To avoid duplicates
        return em.createQuery(cq).getResultList();
    }

    /*cq.where(cb.isNull(adRoot.get(Ad_.removed))); // This restriction is not working as it should
        cq.where(cb.isNull(pictureJoin.get(Picture_.removed)));*/



    public Ad getAdById(int IdAd) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        Root<Ad> adRoot = cq.from(Ad.class);
        cq.select(adRoot);
        cq.where(cb.equal(adRoot.get(Ad_.id), IdAd));
        cq.where(cb.isNull(adRoot.get(Ad_.removed)));
        return em.createQuery(cq).getSingleResult();
    }

    public List<Ad> getAdsByUser(User user) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        Root<Ad> adRoot = cq.from(Ad.class);
        cq.select(adRoot);
        cq.where(cb.equal(adRoot.get(Ad_.user), user));
        cq.where(cb.isNull(adRoot.get(Ad_.removed)));
        return em.createQuery(cq).getResultList();

    }


    public List<String> getAllPicturePaths() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Picture> pictureRoot = cq.from(Picture.class);
        Join<Picture, Ad> pictureAdJoin = pictureRoot.join(Picture_.ad);
        cq.select(pictureRoot.<String>get(Picture_.path));
        cq.where(cb.isNull(pictureRoot.get(Picture_.removed)));
        return em.createQuery(cq).getResultList();

    }

    public List<String> getImagePathsbyAdId(int AdId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Picture> pictureRoot = cq.from(Picture.class);
        Join<Picture, Ad> pictureAdJoin = pictureRoot.join(Picture_.ad);
        cq.select(pictureRoot.<String>get(Picture_.path));
        cq.where(cb.equal(pictureRoot.get(Picture_.ad).get(Ad_.id), AdId));
        cq.where(cb.isNull(pictureRoot.get(Picture_.removed)));
        return em.createQuery(cq).getResultList();

    }

    public List<Picture> getListOfPicsbyAdId(int Adid) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Picture> cq = cb.createQuery(Picture.class);
        Root<Picture> pictureRoot = cq.from(Picture.class);
        Join<Picture, Ad> pictureAdJoin = pictureRoot.join(Picture_.ad);
        cq.select(pictureRoot);
        cq.where(cb.equal(pictureRoot.get(Picture_.ad).get(Ad_.id), Adid));
        cq.where(cb.isNull(pictureRoot.get(Picture_.removed)));
        return em.createQuery(cq).getResultList();


    }

    public List<Picture> getListOfPicsAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Picture> cq = cb.createQuery(Picture.class);
        Root<Picture> pictureRoot = cq.from(Picture.class);
        Join<Picture, Ad> pictureAdJoin = pictureRoot.join(Picture_.ad);
        cq.select(pictureRoot);
        cq.where(cb.isNull(pictureRoot.get(Picture_.removed)));
        return em.createQuery(cq).getResultList();


    }


}
