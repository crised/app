package web;

import enums.City;
import exception.AppException;
import model.Ad;
import model.Picture;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.PictureService;
import util.Resources;

import javax.inject.Inject;
import java.io.File;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;


@RunWith(Arquillian.class)
public class PictureServiceIT {

    @Inject
    Logger logger;

    @Deployment
    public static WebArchive createDeployment() {

        return ShrinkWrap.create(WebArchive.class, "PictureServiceIT.war").

                //Can be resolved with MavenArtifact as well.
                addAsLibraries(new File("/home/crised/.m2/repository/org/primefaces/primefaces/3.4.2/primefaces-3.4.2.jar"),
                        new File("/home/crised/.m2/repository/commons-fileupload/commons-fileupload/1.2.1/commons-fileupload-1.2.1.jar"),
                        new File("/home/crised/.m2/repository/commons-io/commons-io/1.4/commons-io-1.4.jar")).

                addPackages(true,
                        City.class.getPackage(),
                        AppException.class.getPackage(),
                        Ad.class.getPackage(),
                        PictureUtil.class.getPackage(),
                        PictureService.class.getPackage(),
                        Resources.class.getPackage()).

                addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").

                addAsResource("test-persistence.xml", "META-INF/persistence.xml").

                addAsWebInfResource("app-ds.xml");

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


        logger.info("finish test shouldPersistPicture");

    }


}
