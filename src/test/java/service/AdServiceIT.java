package service;

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
import util.Resources;
import web.PictureUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AdServiceIT {

    @Inject
    private Logger log;

    @Deployment
    public static WebArchive createDeployment() {

        return ShrinkWrap.create(WebArchive.class, "AdServiceIT.war").
                addPackages(true,
                        City.class.getPackage(),
                        AppException.class.getPackage(),
                        Ad.class.getPackage(),
                        PictureService.class.getPackage(),
                        Resources.class.getPackage()).
                addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").
                addAsResource("test-persistence.xml", "META-INF/persistence.xml").
                addAsWebInfResource("app-ds.xml");
    }

    @Inject
    AdService adService;

    @Test
    public void shouldReturnAd() {

        Ad ad = adService.getAdById(30);


        List<Picture> pictures = adService.getPicsbyAdId();

        for (Picture p : pictures) {
            System.out.println(p.getId());
        }

        List<String> paths = adService.getAllImagePaths();

        for (String p : paths) {
            log.info(p);
        }


        List<String> pathsById = adService.getByIdImagePaths(30);

        for (String p : pathsById) {
            log.info(p);
        }

        pathsById = adService.getByIdImagePaths(40);

        for (String p : pathsById) {
            log.warning(p);
        }


        assertEquals("This is a failed test", new Integer(30), ad.getId());


    }


}
