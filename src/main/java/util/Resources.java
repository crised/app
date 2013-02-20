package util;

import model.Ad;
import org.infinispan.Cache;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Resources implements Serializable {


    @Resource(lookup = "java:jboss/infinispan/cache/appcache/pagination")
    private Cache<Integer, Ad> cache;


    @Produces
    @ApplicationScoped
    public Cache<Integer,Ad> producesCache(){
        return this.cache;
    }

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


    @Produces
    public ResourceBundle producesResourceBundle() {
        return ResourceBundle.getBundle("messages", Locale.getDefault());
    }







}
