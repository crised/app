package web;

import model.Ad;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2/12/13
 * Time: 3:28 PM
 */

@Named
@ConversationScoped
public class CacheView implements Serializable {

    static final Logger log = Logger.getLogger(CacheView.class);

    @Inject
    CacheBean cacheBean;

    @Inject
    SearchParamBean searchParamBean;

    @Inject
    Conversation conversation;

    private String adString;

    private List<Ad> adShowList;

    private List<Ad> searchResult;

    private List<Integer> adViewedList;

    private int state, fromIndex, toIndex, mp;

    private boolean nextHide, noResults;

    @PostConstruct //After injection is done.
    public void init() {
        adShowList = cacheBean.getSixAds();
        buildString();
        log.info("CacheView Constructed");
    }


    @PreDestroy
    public void destroy() {
        log.info("CacheView Destroyed");
    }


    public void buildString() {
        adString = "";
        for (Ad ad : adShowList) {
            adString = adString + " " + ad.getId();
        }
    }

    public void readString() {
        String[] strings = adString.trim().split("\\s+");
        int counter = 0;
        adViewedList = new ArrayList<>();
        for (String s : strings) {
            adViewedList.add(counter, new Integer(s));
        }

        if (conversation.isTransient())
            conversation.begin();

        adShowList = cacheBean.getSixDifferentAds(adViewedList);

        state = 1; // Browsing
        mp++; //multiplier, first 6 of the viewed List.
        log.info("state: " + state);
    }

    public void normalNextAds() {
        log.info("continue conversation");
        for (Ad ad : adShowList) {
            adViewedList.add(ad.getId());
        }
        adShowList = cacheBean.getSixDifferentAds(adViewedList);

        if (adShowList.size() <= 6) {
            nextHide = true;
            conversation.setTimeout(300000); //300 seconds, 5 minutes
        }

        mp++;

    }

    public String next() {
        log.info("state: " + state);

        switch (state) {
            case 0:
                readString();
                return "index?faces-redirect=true&includeViewParams=true";//&IncludeViewParams=true"; //To initiate conversation
            case 1:
                normalNextAds();
                return "index?faces-redirect=true&includeViewParams=true";
            case 2:
                digestSearchView();
                return "index?faces-redirect=true&includeViewParams=true";

            default:
                return null;
        }
    }

    public String searchResults() {

        //New Conversation - Every time search Button is clicked.

        reset();


        if (conversation.isTransient()) {
            conversation.begin();
        }

        mp++;

        state = 2; // Search Result State


        searchResult = cacheBean.searchEngine(
                searchParamBean.getPrice(),
                searchParamBean.getSurface(),
                searchParamBean.getCity(),
                searchParamBean.isWaterRights(),
                searchParamBean.isFacilities());


        if (searchResult.size() > 0) digestSearchView();
        if (searchResult.isEmpty()) noResults = true;
        return "index?faces-redirect=true&includeViewParams=true"; //To initiate conversation


    }

    public void digestSearchView() {

        if (toIndex == 0) toIndex = 6;
        if (searchResult.size() <= toIndex) {
            nextHide = true;

            if (!conversation.isTransient())
                conversation.setTimeout(300000);

            adShowList = searchResult.subList(fromIndex, searchResult.size());

        } else {
            adShowList = searchResult.subList(fromIndex, toIndex);
            fromIndex = fromIndex + 6;
            toIndex = toIndex + 6;
            nextHide=false;
        }
    }

    public String reset() {

        searchResult = null;
        adShowList = null;
        adViewedList = null;
        fromIndex=0;
        toIndex=0;
        mp=0;
        noResults = false;

        if (!conversation.isTransient()) conversation.end();
        return "index?faces-redirect=true";
    }


    public List<Ad> getAdList() {
        return adShowList;
    }

    public void setAdList(List<Ad> adList) {
        this.adShowList = adList;
    }

    public String getAdString() {
        return adString;
    }

    public void setAdString(String adString) {
        this.adString = adString;
    }

    public SearchParamBean getSearchParamBean() {
        return searchParamBean;
    }

    public void setSearchParamBean(SearchParamBean searchParamBean) {
        this.searchParamBean = searchParamBean;
    }

    public int getState() {
        return state;
    }

    public boolean isNextHide() {
        return nextHide;
    }

    public void setNextHide(boolean nextHide) {
        this.nextHide = nextHide;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public boolean isNoResults() {
        return noResults;
    }

    public void setNoResults(boolean noResults) {
        this.noResults = noResults;
    }
}


