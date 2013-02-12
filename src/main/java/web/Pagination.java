package web;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class Pagination implements Serializable {


    @Resource(lookup = "java:jboss/infinispan/container/appcache")
    private EmbeddedCacheManager cacheManager;

    Cache cache;


    static final Logger log = Logger.getLogger(Pagination.class);

    int counter;


    public void init() {


        log.info(cacheManager.isDefaultRunning());

        cache = cacheManager.getCache("pagination", false);
        try {
            cache.put(0, "Hello");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @PreDestroy
    public void destroyed(){
        log.info("Pagination destroyed");
    }


    public void log() {

        try{

            if (cache.get(0) == null) {

                cache.put(0, "Refreshed: " + counter + " times");
                counter++;
                log.info(cache.get(0));

            }

        }         catch(Exception e){
            log.error(e.getMessage());
        }


        log.info(cache.get(0));

    }

}
