package web;

import enums.City;
import enums.Price;
import enums.Region;
import enums.Surface;
import model.Ad;
import org.infinispan.Cache;
import org.jboss.logging.Logger;
import service.AdService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date: 2/14/13
 * Time: 11:45 AM
 */

public class CacheBean implements Serializable {

    static final Logger log = Logger.getLogger(CacheBean.class);

    @Inject
    Cache<Integer, Ad> cache;

    @Inject
    AdService adService;

    private Cache<Integer, Ad> cleanCache; //Without Goofy
    private List<Ad> adList;


    public CacheBean() {
        log.info("CacheBean constructor!");
    }

    @PostConstruct
    public void initCaches() {
        log.info("CacheBean AppScoped constructed");
    }

    /* Never use cache.values()!
       Values are stored in a list and in the cached
       directly from CriteriaQuery.*/


    public void fillAdListFromDB() {
        long t1 = System.currentTimeMillis();
        adList = adService.getAll();
        long t2 = System.currentTimeMillis();
        long time = t2 - t1;
        log.info("time to get Ads from Db: ");
        log.info(time);

    }

    public void fillAdListFromCache() {
        long t1 = System.currentTimeMillis();
        cleanCache();
        if (adList != null) adList = null;  //adList can be stale
        adList = new ArrayList<Ad>(cleanCache.values());
        long t2 = System.currentTimeMillis();
        long time = t2 - t1;
        log.info("time to get Ads from Cache: ");
        log.info(time);

    }

    public void fillCache() {  //Cache always get filled from List.

        cache.put(-1, new Ad()); // Goofy entrance, used to check if it has expired.
        for (Ad ad : adList) {
            cache.put(ad.getId(), ad);
        }

    }

    public List<Ad> getAdList() {

        // Data can come from DB or Cache.

        if (isExpired()) {
            log.info("cache has expired");
            fillAdListFromDB();
            fillCache();
            return adList;
        }

        log.info("cache hasn't expired");
        if (adList == null) fillAdListFromCache();
        return adList;
    }

    public List<Ad> getSixAds() {

        List<Ad> sixAdsList = getAdList().subList(0, 6);
        Collections.shuffle(sixAdsList);
        return sixAdsList;
    }

    public List<Ad> getSixDifferentAds(List<Integer> adViewedList) {

        //Easier to Work directly with a Map (Cache)

        Cache<Integer, Ad> temp = getCleanCache();

        for (Integer adId : adViewedList) {
            temp.remove(adId);
        }

        List<Ad> sixDiffAdsList = new ArrayList<>(temp.values());

        if (sixDiffAdsList.size() >= 6) {
            return sixDiffAdsList.subList(0, 6);
        }

        return sixDiffAdsList;
    }

    public Cache<Integer, Ad> getCleanCache() {
        if (isExpired()) {
            fillAdListFromDB();
            fillCache();
        }

        cleanCache();
        return cleanCache;

    }

    public boolean isExpired() {
        if (!cache.containsKey(-1))
            return true;
        return false;
    }

    public void cleanCache() { //remove goofy entrance
        cleanCache = cache;
        cache.remove(-1);
    }

    public List<Ad> searchEngine(Price price, Surface surface, Region region,
                                 City city, boolean waterRights,
                                 boolean facilities) {

        List<Ad> search = new ArrayList<>();

        for (Ad ad : getAdList()) {
            while (ad != null) {
                if (facilities==true){

                }
                if (ad.getFacilities() == facilities) {
                    if (ad.getWaterRights() == waterRights) {
                        search.add(ad);
                    }
                }
                break;
            }
        }
        return search;
    }


}
