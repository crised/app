package service;

import model.Picture;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.logging.Logger;

@Stateless
//@Loggable
public class PictureService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private Logger log;

    public Picture newPic() {

        Picture pic = new Picture();
        em.persist(pic);
        return pic;

    }

    public void createPic(Picture pic) {

        em.persist(pic);

    }

    public Picture searchPic(Picture pic) {

        return em.find(Picture.class, pic.getId());
    }

    public void updatePicture(Picture pic){
        em.merge(pic);
    }


}

