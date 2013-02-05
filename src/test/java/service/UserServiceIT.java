package service;

import enums.City;
import enums.Roles;
import exception.AppException;
import model.Ad;
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
import java.util.logging.Logger;

import static junit.framework.Assert.*;


@RunWith(Arquillian.class)
public class UserServiceIT {

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
    UserService userService;

    private User createUser() {

        User user = new User();
        user.setId("crised@gmail.com");
        user.setName("Cristian");
        user.storeHashPassword("admin");
        return user;

    }


    @Test
    public void shouldCreateUser() {

        User user = createUser();

        log.info(user.getId());
        try {
           userService.createUser(user);
            assertNotNull(user.getId());

        } catch (Exception e) {
            assertNotNull(user);
            log.severe(e.getMessage());

        }
      //  log.info(user.getId().toString()); //takes an id
    }

    @Test
    public void shouldReadUser() {

        User readUser = userService.findAll().get(0);
        assertNotNull(readUser.getId());

    }

    @Test
    public void shouldReadUserRole() {

        User readUser = userService.findAll().get(0);
        log.warning(readUser.toString());
        //Roles roles = readUser.getRole().getRole(); //Enum then Entity
        assertEquals("failed", readUser.getRole().getRole(), Roles.REGISTERED);
    }

    @Test
    public void shouldFindUserByLogin() {

        User readUser = userService.findUser("crised@gmail.com");
        assertEquals("fail", readUser.getId(), "crised@gmail.com");
    }


}
