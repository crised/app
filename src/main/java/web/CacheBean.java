package web;

import model.Ad;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.jboss.logging.Logger;
import service.AdService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Date: 2/14/13
 * Time: 11:45 AM
 */

@ApplicationScoped
public class CacheBean implements Serializable {

    @Resource(lookup = "java:jboss/infinispan/container/appcache")
    private EmbeddedCacheManager cacheManager;

    @Inject
    AdService adService;

    private Cache<Integer, Ad> cache;
    private List<Ad> adList;

    static final Logger log = Logger.getLogger(CacheBean.class);

    public CacheBean() {
        log.info("CacheBean constructor!");
    }

    @PostConstruct
    public void initCaches() {
        log.info("CacheBean AppScoped constructed");
        cache = cacheManager.getCache("pagination", false);
    }

    /* Never use cache.values()! */


    public List<Ad> refreshCache() {
        adList = adService.getAll();
        cache.put(-1, new Ad()); // Goofy entrance, just to check if it has expired.
        for (Ad ad : adList) {
            cache.put(ad.getId(), ad);
        }
        return adList;
    }

    public List<Ad> completeList() {
        if (!cache.containsKey(-1)) {
            log.info("cache has expired");
            return refreshCache();
        }

        log.info("cache hasn't expired");
        return adList;
    }

    public Cache<Integer, Ad> getCache() {
        if (!cache.containsKey(-1))
            refreshCache();
        Cache<Integer, Ad> cleanCache = cache;
        cleanCache.remove(-1);
        return cleanCache;

    }



    /*if (cache.containsKey(-1)) cache.remove(-1);
        adList = new ArrayList<>(cache.values());*/
}
