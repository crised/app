package service;

import model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Stateless
//@Loggable
public class AdService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private Logger log;

    public Ad createAd(Ad ad) {
        em.persist(ad);
        return ad;
    }

    public Ad updateAd(Ad ad) {
        em.merge(ad);
        return ad;
    }

    public List<Ad> getAll(){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        Root<Ad> adRoot = cq.from(Ad.class);
        cq.select(adRoot);
        cq.where(cb.isNull(adRoot.get(Ad_.removed)));
        return em.createQuery(cq).getResultList();
    }

    public Ad getAdById(int IdAd) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        Root<Ad> adRoot = cq.from(Ad.class);
        cq.select(adRoot);
        cq.where(cb.equal(adRoot.get(Ad_.id), IdAd));
        cq.where(cb.isNull(adRoot.get(Ad_.removed)));
        return em.createQuery(cq).getSingleResult();
    }

    public List<Ad> getAdsByUser(User user){

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
