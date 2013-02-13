package web;

import model.Ad;
import org.infinispan.Cache;
import org.jboss.logging.Logger;
import service.AdService;
import util.Loggable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2/12/13
 * Time: 3:28 PM
 */

@Loggable
public abstract class CacheService {

    /*@Resource(lookup = "java:jboss/infinispan/container/appcache")
    private EmbeddedCacheManager cacheManager; */

    /*
    @Inject
    private EmbeddedCacheManager cacheManager; */

    @Inject
    private Cache<Integer, Ad> cache;


    @Inject
    AdService adService;

    static final Logger log = Logger.getLogger(CacheService.class);

    public void initCache() {
       /* cache = cacheManager.getCacheMethod("pagination", true);   */

    }


    public void populateCache() {

        log.info("Entering populateCache");
        List<Ad> adList = adService.getAll();

        for (Ad ad : adList) {
            if (cache == null) log.warn("cache is null");
            cache.put(ad.getId(), ad);
        }

    }

    public List<Ad> getCompleteList() {

        log.info("In getCompleteList():");

        return new ArrayList<>(getCacheMethod().values());


    }


    public Cache<Integer, Ad> getCacheMethod() {

        if (cache.get(adService.getAll().get(0).getId())==null) // Only way to check! cache.size is a bad way!
        {

            log.info("cache should be  Empty, but it's not cache size: " + cache.size());
            populateCache();
            return cache;
        }

        return cache;
    }
    /*
      public List<Ad> getSubList(int fromIndex, int toIndex) {

        log.info("Cache List Size from getSublist: " + getCacheMethod().size());
        log.info("Complete List Size from getSublist:" + getCompleteList().size());


        if (getCompleteList().size() >= 1) {
            int maxToIndex = getCompleteList().size() - 1;
            if (toIndex > maxToIndex) toIndex = maxToIndex;

        } else return null; //Empty List

        return getCompleteList().subList(fromIndex, toIndex);

    }
     */
}
