package service;

import enums.City;
import enums.Roles;
import exception.AppException;
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

import static junit.framework.Assert.assertEquals;


@RunWith(Arquillian.class)
public class LoginServiceIT {

    @Inject
    private Logger log;


    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "LoginService.war").addPackage(LoginService.class.getPackage()).addPackage(Resources.class.getPackage()).

                addPackage(User.class.getPackage()).

                addPackage(City.class.getPackage()).//enums

                addPackage(AppException.class.getPackage()).

                addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").

                addAsResource("test-persistence.xml", "META-INF/persistence.xml").

                addAsWebInfResource("primefaces1-ds.xml");

    }

    @Inject
    LoginService loginService;

    private User createUser() {

        User user = new User();
        user.setLogin("crised@gmail.com");
        user.setName("Cristian");
        user.storeHashPassword("admin");
        Role role = new Role();
        role.setRole(Roles.REGISTERED);
        user.setRole(role);
        return user;

    }


    @Test
    @Ignore
    public void shouldCreateUser() {

        User user = createUser();

        log.info(user.getLogin());
        if (user.getId() != null) log.info(user.getId().toString()); // no tiene id
        try {
            user = loginService.createUser(user);
        } catch (Exception e) {
            log.severe(e.getMessage());

        }
        log.info(user.getId().toString()); //takes an id
        assertEquals("failed", user.getLogin(), "crised@gmail.com");
    }

    @Test
    public void shouldReadUser() {

        User readUser = loginService.findUserById(1);
        assertEquals("failed", new Integer(1), readUser.getId());

    }

    @Test
    public void shouldReadUserRole() {

        User readUser = loginService.findUserById(1);
        Roles roles = readUser.getRole().getRole(); //Enum then Entity
        assertEquals("failed", roles, Roles.REGISTERED);
    }

    @Test
    public void shouldFindUserByLogin() {

        User readUser = loginService.findUserByLogin("crised@gmail.com");
        assertEquals("fail", readUser.getLogin(), "crised@gmail.com");
    }

    @Test
    public void shouldDetectIfUserExists() {

        User user = createUser();
        String message = null;


        try {
            loginService.createUser(user);
        } catch (AppException e) {
            message = e.getMessage();
            log.severe(message);

        }

        assertEquals("failed", message, "User already Exists");


    }


}
