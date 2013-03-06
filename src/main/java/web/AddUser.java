package web;

import exception.UserException;
import model.User;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Logger;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class AddUser extends Messages implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private User user;

    @Inject
    private Mail mail;

    static final Logger log = Logger.getLogger(AddUser.class);

    private String checkMail;


    @Size(min = 7, max = 20, message = "{user.passwordLength}")
    @NotEmpty(message = "{user.passwordError}")
    private String password1;

    @Size(min = 7, max = 20, message = "{user.passwordLength}")
    @NotEmpty(message = "{user.passwordError}")
    private String password2;

    public void actionListener() {

        if (!password1.equals(password2)) {
            log.info("user.passwordDoNotMatch");
            addSimpleMessage(rB.getString("user.passwordDoNotMatch"));
        }

    }


    public String action() {

        if (!user.getId().equals(checkMail)) {
            log.info("user.emailDoNotMatch");
            addSimpleMessage(rB.getString("user.emailDoNotMatch"));
            return null;
        }

        if (!password1.equals(password2)) {
            log.info("user.passwordDoNotMatch");
            addSimpleMessage(rB.getString("user.passwordDoNotMatch"));
            return null;
        }


        try {
            user.storeHashPassword(password1);
            String link = getActivationLink();
            user.setConfirmLink(link);
            userService.signUpUser(user); // Check if user exists verified in EJB
            mail.SendMail(user, link);
            return "addUserSuccess.xhtml?faces-redirect=true";

        } catch (UserException e) {

            log.warn(e);
            addSimpleMessage(e.getMessage());
            return null;

        }

    }


    public String logout() {
        //Only works on FacesContext
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String getActivationLink() {

        return "https://drip.cl/app-1.0/confirm/" +
                User.getHashPassword(user.getId() + System.currentTimeMillis());

    }

    @PostConstruct
    public void postConstruct() {
        log.info("AddUser Constructed");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("AddUser destroyed");
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

    public String getCheckMail() {
        return checkMail;
    }

    public void setCheckMail(String checkMail) {
        this.checkMail = checkMail;
    }
}
