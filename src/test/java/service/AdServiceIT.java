package service;

import enums.City;
import exception.AppException;
import model.Ad;
import model.Picture;
import model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.Resources;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

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

        Ad ad = null;

        try {

            ad = adService.getAll().get(0); // Takes first ad of the list.

            /*
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

            */

            User user = new User();
            user.setId(31);

            List<Ad> ads = adService.getAdsByUser(user);

            for (Ad p : ads){
                log.info(p.getId().toString());

                for(Picture pic : p.getPictureList()){
                    log.info(pic.getPath());
                }
            }



        } catch (Throwable cause) {
            log.severe(cause.getMessage());
            log.severe("no result found");
        }

        assertNotNull(ad);



    }


}
