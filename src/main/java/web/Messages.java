package web;

import org.jboss.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ResourceBundle;

/**
 * Date: 2/5/13
 * Time: 10:35 AM
 */

abstract class Messages {

    @Inject
    transient ResourceBundle rB;


    protected void addSimpleMessage(String message){

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(message);
        facesContext.addMessage(null,facesMessage);


    }
}
