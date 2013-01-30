package service;

import exception.AppException;
import model.Ad;
import model.Ad_;
import model.Picture;
import model.Picture_;

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

    public Ad getAdById(int IdAd) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        Root<Ad> adRoot = cq.from(Ad.class);
        cq.select(adRoot);
        cq.where(cb.equal(adRoot.get(Ad_.id), IdAd));
        try {
            return em.createQuery(cq).getSingleResult();

        } catch (NoResultException e) {
            throw new AppException("No pics found for that Ad Id");
        }
    }


    public List<Picture> getPicsbyAdId() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Picture> cq = cb.createQuery(Picture.class);
        Root<Picture> adRoot = cq.from(Picture.class);
        Join<Picture, Ad> pictureAdJoin = adRoot.join(Picture_.ad);
        //SELECT clause is omitted
        try {
            return em.createQuery(cq).getResultList();

        } catch (NoResultException e) {
            throw new AppException("No pics found for that Ad Id");
        }

    }

    public List<String> getAllImagePaths() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Picture> pictureRoot = cq.from(Picture.class);
        Join<Picture, Ad> pictureAdJoin = pictureRoot.join(Picture_.ad);
        cq.select(pictureRoot.<String>get(Picture_.path));
        try {
            return em.createQuery(cq).getResultList();

        } catch (NoResultException e) {
            throw new AppException("No pics found");
        }

    }

    public List<String> getByIdImagePaths(int AdId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Picture> pictureRoot = cq.from(Picture.class);
        Join<Picture, Ad> pictureAdJoin = pictureRoot.join(Picture_.ad);
        cq.select(pictureRoot.<String>get(Picture_.path));
        cq.where(cb.equal(pictureRoot.get(Picture_.ad).get(Ad_.id), AdId));
        try {
            return em.createQuery(cq).getResultList();

        } catch (NoResultException e) {
            throw new AppException("No paths found");
        }

    }


}
