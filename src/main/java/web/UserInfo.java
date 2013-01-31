package web;


import enums.Roles;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginContext;
import java.io.Serializable;

@Named
@RequestScoped
public class UserInfo implements Serializable {

   /* @Inject
    @SessionScoped
    private transient LoginContext lc;  */

    String s1,s2,s3;




    @PostConstruct
    public void info(){

        s1 = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        s2 = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
        s3 = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Roles.REGISTERED.name()));
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }
}
