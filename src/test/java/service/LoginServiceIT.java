package service;

import enums.City;
import enums.Roles;
import exception.AppException;
import model.Ad;
import model.Role;
import model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.Resources;

import javax.inject.Inject;
import java.util.logging.Logger;

import static junit.framework.Assert.*;


@RunWith(Arquillian.class)
public class LoginServiceIT {

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
    LoginService loginService;

    private User createUser() {

        User user = new User();
        user.setLogin("crised@gmail.com");
        user.setName("Cristian");
        user.storeHashPassword("admin");
        //Role role = new Role();
        //role.setRole(Roles.REGISTERED);
        //user.setRole(role);
        return user;

    }


    @Test
    public void shouldCreateUser() {

        User user = createUser();

        log.info(user.getLogin());
        try {
           loginService.createUser(user);
            assertNotNull(user.getId());

        } catch (Exception e) {
            assertNotNull(user);
            log.severe(e.getMessage());

        }
      //  log.info(user.getId().toString()); //takes an id
    }

    @Test
    public void shouldReadUser() {

        User readUser = loginService.findAll().get(0);
        assertNotNull(readUser.getId());

    }

    @Test
    public void shouldReadUserRole() {

        User readUser = loginService.findAll().get(0);
        log.warning(readUser.toString());
        //Roles roles = readUser.getRole().getRole(); //Enum then Entity
        assertEquals("failed", readUser.getRole().getRole(), Roles.REGISTERED);
    }

    @Test
    public void shouldFindUserByLogin() {

        User readUser = loginService.findUserByLogin("crised@gmail.com");
        assertEquals("fail", readUser.getLogin(), "crised@gmail.com");
    }


}
