package service;


import exception.AppException;
import model.User;
import model.User_;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private Logger log;


    public User createUser(User user) {

        if (!DoesLoginAlreadyExists(user)) {
            em.persist(user);
            return user; // Becomes Managed therefore grabs an id.
        } else {
            throw new AppException("User already Exists");
        }
    }

    public Boolean DoesLoginAlreadyExists(User user) {

        if (user == null) throw new AppException("Null Login");

        try {
            findUserByLogin(user.getId());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }


    public User findUserById(Integer Id) {

        User user = null;

        try {
            user = em.find(User.class, Id); //returns null if no user
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;

    }

    public List<User> findAll(){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User>  cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        return em.createQuery(cq).getResultList();


    }


    public User findUserByLogin(String login) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        cq.where(cb.equal(userRoot.get(User_.id), login));
        return em.createQuery(cq).getSingleResult();


    }


}
