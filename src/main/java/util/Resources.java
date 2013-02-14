package util;

import model.Ad;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import sun.security.krb5.internal.APOptions;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Resources implements Serializable {


    @Produces
    @Resource(lookup = "java:jboss/infinispan/container/appcache")
    private EmbeddedCacheManager cacheManager;

    /*@Produces
    @ApplicationScoped
    public Cache<Integer,Ad> producesCache(){
        return cacheManager.getCache("pagination",false);
    }*/

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


    @Produces
    public ResourceBundle producesResourceBundle() {
        return ResourceBundle.getBundle("messages", Locale.getDefault());
    }







}
