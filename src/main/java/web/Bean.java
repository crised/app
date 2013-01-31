package web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class Bean {

    private String hello;


    @PostConstruct
    public void start() {

        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (login == null) {
            hello = "Hello Guest!";
        } else hello = "Hello " + login + "!";

    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
