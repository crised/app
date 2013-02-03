package util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Resources {

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


   @Produces
    public ResourceBundle producesResourceBundle() {
        return ResourceBundle.getBundle("messages", Locale.getDefault());
    }
}
