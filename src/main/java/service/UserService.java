package service;


import exception.UserException;
import model.User;
import model.User_;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

@Stateless
public class UserService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Inject
    ResourceBundle rB;


    static final Logger log = Logger.getLogger(UserService.class);

    public User createUser(User user) {

        em.persist(user);
        log.error("DO NOT USE THIS METHOD");
        return user;

    }

    public User findUser(String id) {

        return em.find(User.class, id);

    }

    @PermitAll
    public User signUpUser(User user)
            throws UserException {

        User existingUser = em.find(User.class, user.getId());

        // CANT USE RESOURCE BUNDLE ON EJB

        if (existingUser != null) {

            log.warn("Duplicate User");
            throw new UserException("Duplicate User");

        }

        try {
            em.persist(user);
        } catch (EntityExistsException e) {
            log.error("Duplicate User", e);
            throw new UserException("Duplicate User");
        }

        return user;

    }

    @RolesAllowed({"REGISTERED"})
    public User modifyPassword(User user) {

        User oldUser = em.find(User.class, user.getId());

        if (oldUser.getMailConfirmed() != true) {
            String message = rB.getString("user.notConfirmed");
            log.warn(message);
            throw new UserException(message);

        }

        if (oldUser == null) {
            String message = rB.getString("user.notFoundChangePassword");
            log.error(message);
            throw new UserException(message);
        }

        if (oldUser.getPassword() == user.getPassword()) {
            String message = rB.getString("user.samePassword");
            log.warn(message);
            throw new UserException(message);
        }

        return em.merge(em.find(User.class, user.getId()));

    }

    public Boolean activateUser(String link) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        cq.where(cb.equal(userRoot.get(User_.confirmLink), link));

        User user = null;

        try {
            user = em.createQuery(cq).getSingleResult();
        } catch (PersistenceException e) {
            return false;
        }

        user.setMailConfirmed(true);
        return true;
    }


    public List<User> findAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        return em.createQuery(cq).getResultList();


    }


}
