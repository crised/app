package web;

import model.User;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Logger;
import service.UserService;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@ViewScoped
public class AddUser extends Messages implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private User user;

    static final Logger log = Logger.getLogger(AddUser.class);


    @Size(min = 7, max = 20, message = "{user.passwordLength}")
    @NotEmpty(message = "{user.passwordError}")
    private String password1, password2;

    public String action() {

        if (!password1.equals(password2)) {
            log.info("user.passwordDoNotMatch");
            addSimpleMessage(rB.getString("user.passwordDoNotMatch"));
            return null;
        }



        try {
            user.storeHashPassword(password1);
            userService.signUpUser(user); // Check if user exists
            return "auth/success?faces-redirect=true";
        } catch (Exception e) {
            log.error("user.passwordError", e);
            addSimpleMessage(rB.getString("user.passwordError"));
            return null;

        }

    }


    public String logout() {
        //Only works on FacesContext
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
