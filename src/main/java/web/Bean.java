package web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class Bean {

    private String hello;

    private BigDecimal test;

    @PostConstruct
    public void start() {

        test = new BigDecimal(15_500_000);

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

    public BigDecimal getTest() {
        return test;
    }

    public void setTest(BigDecimal test) {
        this.test = test;
    }
}
