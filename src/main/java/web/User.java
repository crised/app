package web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Date: 2/20/13
 * Time: 1:59 PM
 */

@Named
@RequestScoped
public class User {

    String userId;   //Mail


    @PostConstruct
    public void init() {
        userId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public String logout() {
        //Only works on FacesContext
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
