package web;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.TimeUnit;


@Named
@RequestScoped
public class Pagination {


    @Inject
    EmbeddedCacheManager cacheManager;

    Cache cache;


    static final Logger log = Logger.getLogger(Pagination.class);

    int counter;


    @PostConstruct
    public void method() {

        cache = cacheManager.getCache("pagination",false);

        cache.put(0, "Hello");
    }

    public void log(){

        if(cache.get(0)==null){

            cache.put(0, "Refreshed: " + counter + "times");
            counter++;

        }

        log.info(cache.get(0));

    }

}
