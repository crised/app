package web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
@RequestScoped
public class AjaxBean {

    private String message;

    public String getMessage() {
        return message;
    }

    public void handleEvent(AjaxBehaviorEvent event) {
        message = "Hello World";
    }

}
