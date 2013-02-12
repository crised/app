package web;

import model.Ad;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.jboss.logging.Logger;
import service.AdService;
import util.Loggable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2/12/13
 * Time: 3:28 PM
 */

@Loggable
public abstract class CacheService {

    @Resource(lookup = "java:jboss/infinispan/container/appcache")
    private EmbeddedCacheManager cacheManager;

    private Cache<Integer, Ad> cache;


    @Inject
    AdService adService;

    static final Logger log = Logger.getLogger(CacheService.class);

    @PostConstruct
    public void initCache() {
        cache = cacheManager.getCache("pagination", false);

    }


    public void populateCache() {

        List<Ad> adList = adService.getAll();


        if (cache != null) {

            log.info(cacheManager.isDefaultRunning());

            for (Ad ad : adList) {
                if (cache == null) log.warn("cache is null");
                cache.put(ad.getId(), ad);
            }

        } else log.error("cache is null");

        log.info(cache.size());


    }

    public List<Ad> getCompleteList() {

        log.info("Cache List Size from getCompleteList(): " + getCache().size());

        return new ArrayList<>(getCache().values());

    }

    public List<Ad> getSubList(int fromIndex, int toIndex) {

        log.info("Cache List Size from getSublist: " + getCache().size());
        log.info("Complete List Size from getSublist:" + getCompleteList().size());



        if (getCompleteList().size() >= 1) {
            int maxToIndex = getCompleteList().size() - 1;
            if (toIndex > maxToIndex) toIndex = maxToIndex;

        } else return null; //Empty List

        return getCompleteList().subList(fromIndex, toIndex);

    }

    public Cache<Integer, Ad> getCache() {

        if (cache == null) {
            initCache();
            populateCache();
            return cache;
        }

        if (cache.isEmpty()) {
            populateCache();
            return cache;

        }

        return cache;
    }
}
