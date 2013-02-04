package web;

import model.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class Login {

    static final Logger log = Logger.getLogger(Login.class);

    @Inject
    User user;

    private String plainPassword;



    public String authenticate(){

        user.storeHashPassword(plainPassword);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        String s1 = user.getId();
        String s2 = user.getPassword();

        try{
            request.login(s1,s2);
            log.info(externalContext.getRemoteUser());
        } catch(ServletException e){
            log.error("Failed to authenticate user",e);

        }


        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
}
