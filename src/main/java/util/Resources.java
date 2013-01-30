package util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

public class Resources {

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

   /* @Produces
    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("/messages", locale )
    }  */
}
