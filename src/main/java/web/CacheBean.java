package web;

import enums.City;
import enums.Price;
import enums.Surface;
import model.Ad;
import org.infinispan.Cache;
import org.jboss.logging.Logger;
import service.AdService;

import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
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


    /* Never use cache.values()!
       Values are stored in a list and in the cached
       directly from CriteriaQuery.*/


    public void fillAdListFromDB() {
        adList = adService.getAll();
    }

    public void fillAdListFromCache() {
        cleanCache();
        if (adList != null) adList = null;  //adList can be stale
        adList = new ArrayList<Ad>(cleanCache.values());


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


    public List<Ad> searchEngine(Price price, Surface surface, City city,
                                 boolean waterRights,
                                 boolean facilities) {

        List<Ad> search = new ArrayList<>();


        for (Ad ad : getAdList()) {
            while (true) {

                if (!isInPriceRange(
                        price.getLowerPrice(),
                        ad.getPrice(),
                        price.getHigherPrice()))
                    break;

                if (!isInSurfaceRange(
                        surface.getLowerSurface(),
                        ad.getSurface(),
                        surface.getHigherSurface()))

                    break;

                if (!isInCity(city,
                        ad.getCity()))
                    break;

                if (!checkBoolean(waterRights,
                        ad.getWaterRights()))
                    break;

                if (!checkBoolean(facilities,
                        ad.getFacilities()))
                    break;

                search.add(ad);
                break;

            }
        }
        return search;
    }

    public boolean isInPriceRange(BigDecimal lowerRange,
                                  BigDecimal price,
                                  BigDecimal higherRange) {
        if (lowerRange == null && higherRange == null) return true;

        if (price.compareTo(lowerRange) >= 0) {
            if (higherRange == null) return true;
            if (price.compareTo(higherRange) == -1) return true;
        }
        return false;
    }

    public boolean isInSurfaceRange(Float lowerSurface,
                                    Float surface,
                                    Float highSurface) {
        if (lowerSurface == null && highSurface == null) return true;

        if (surface >= lowerSurface) {
            if (highSurface == null) return true;
            if (surface < highSurface) return true;

        }
        return false;
    }

    public boolean isInCity(City selectedCity, City adCity) {

        if (selectedCity == City.ALL) return true;

        if (selectedCity.getGroup() != null)
        //Whether is all cities of a region
        {
            if (selectedCity.getRegion() == adCity.getRegion()) {
                return true;
            }
            return false;

        }

        if (selectedCity == adCity) return true;

        return false;
    }

    public boolean checkBoolean(boolean selectedWaterRights,
                                boolean adWaterRights) {
        if (selectedWaterRights == false) return true; //No option Selected
        if (adWaterRights == true) return true;
        return false;
    }

    /*
    SingleAd
     */

    public Ad getAd(int adId) {

        return getCleanCache().get(adId);

    }

    /*
    UserAd
     */

    public List<Ad> getAdsByUser(String userId) {

        List<Ad> filteredList = new ArrayList<>();

        for (Ad ad : getAdList()) {
            if (ad.getUser().getId().trim().equals(userId.trim())) filteredList.add(ad);

        }
        log.info(filteredList.size());
        Collections.sort(filteredList);
        return filteredList;
    }

    public void removeAd(Ad ad){
        cache.remove(ad.getId());
    }




}
