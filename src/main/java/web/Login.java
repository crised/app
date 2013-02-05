package web;

import model.User;
import org.jboss.logging.Logger;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class Login extends Messages {


    @Inject
    UserService userService;

    static final Logger log = Logger.getLogger(Login.class);


    private String userId, plainPassword;

    private Boolean showSendMailButton;


    public String authenticate() {

        User foundedUser = userService.findUser(userId);

        // Following ifs are only to aid user on sign in
        if (foundedUser == null) {

            String message = rB.getString("user.idDoesNotExist");
            log.info(message);
            addSimpleMessage(message);
            return null;

        }

        if (foundedUser.getMailConfirmed() != true) {

            String message = rB.getString("user.notConfirmed");
            log.info(message);
            addSimpleMessage(message);
            showSendMailButton = true;
            return null;


        }


        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(userId, User.getHashPassword(plainPassword)); //REALM query for only actives user
            log.info(externalContext.getRemoteUser());
            return "/auth/HomePage.xhtml";

        } catch (ServletException e) {
            log.error("Failed to authenticate user", e);

        }


        return null;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getShowSendMailButton() {
        return showSendMailButton;
    }

    public void setShowSendMailButton(Boolean showSendMailButton) {
        this.showSendMailButton = showSendMailButton;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
}
