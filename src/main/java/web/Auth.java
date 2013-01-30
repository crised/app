package web;

import model.User;
import service.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Auth {

    @Inject
    private LoginService loginService;

    private User user;
    private String password1, password2;

    public String action() {

        if (password1.equals(password2)) {
            loginService.createUser(user);
            return "auth/sucess?faces-redirect=true";
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage("Passwords are different!");
        facesContext.addMessage(null, facesMessage);
        return null;


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
