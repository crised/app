package service;

import model.Picture;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.Resources;

import javax.inject.Inject;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;


@RunWith(Arquillian.class)
public class PictureServiceIT {


    @Inject
    private Logger log;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "PictureServiceIT.war").addPackage(PictureService.class.getPackage()).addPackage(Resources.class.getPackage()).

                addPackage(Picture.class.getPackage()).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").

                addAsResource("test-persistence.xml", "META-INF/persistence.xml").

                addAsWebInfResource("primefaces1-ds.xml");

    }

    @Inject
    PictureService pictureService;

    @Test
    public void shouldCreatePicture() {

        Picture pic = new Picture();
        pic.setDescription("ranco");
        assertEquals("failed", "ranco", pic.getDescription());

    }

    @Test
    public void shouldPersistPicture() {

        Picture pic = new Picture();
        pic.setDescription("ranco");
        pictureService.createPic(pic);

        //retrieve pic
        Picture pic2 = pictureService.searchPic(pic);
        assertEquals("failed", pic2.getId(), pic.getId());


        log.info("finish test shouldPersistPicture");

    }


}
