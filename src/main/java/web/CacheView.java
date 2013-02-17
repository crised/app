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
    private Conversation conversation;

    private String adString;

    private List<Ad> adList;

    private List<Integer> adViewedList;

    private boolean switchButton, disableButton;


    @PostConstruct //After injection is done.
    public void init() {
        adList = cacheBean.getSixAds();
        buildString();
        log.info("CacheView Constructed");
    }

    @PreDestroy
    public void destroy() {
        log.info("CacheView Destroyed");
    }

    public void buildString() {
        adString = "";
        for (Ad ad : adList) {
            adString = adString + " " + ad.getId();
        }
        log.info("Render List: " + adString);
    }

    public String readString() {
        String[] strings = adString.trim().split("\\s+");
        int counter = 0;
        adViewedList = new ArrayList<>();
        for (String s : strings) {
            adViewedList.add(counter, new Integer(s));
        }
        log.info("Viewed Ads id: ");

        for (Integer i : adViewedList) {
            log.info(i);
        }

        if (conversation.isTransient())
            conversation.begin();

        adList = cacheBean.getSixDifferentAds(adViewedList);

        switchButton = true;

        return "view.xhtml?faces-redirect=true";

    }

    public void continueConversation() {
        log.info("continue conversation");
        for (Ad ad : adList) {
            adViewedList.add(ad.getId());
        }
        adList = cacheBean.getSixDifferentAds(adViewedList);

        if (adList.size() < 5) {
            disableButton = true;
            conversation.setTimeout(300000); //300 seconds, 5 minutes
        }

    }

    public void getSearchResults() {
        adList = cacheBean.searchEngine(
                searchParamBean.getPrice(),
                searchParamBean.getSurface(),
                searchParamBean.getRegion(),
                searchParamBean.getCity(),
                searchParamBean.isWaterRights(),
                searchParamBean.isFacilities());
    }

    public void resetSearch(){

        if(!conversation.isTransient()) conversation.end();
    }


    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public String getAdString() {
        return adString;
    }

    public void setAdString(String adString) {
        this.adString = adString;
    }

    public boolean isSwitchButton() {
        return switchButton;
    }

    public void setSwitchButton(boolean switchButton) {
        this.switchButton = switchButton;
    }

    public boolean isDisableButton() {
        return disableButton;
    }

    public void setDisableButton(boolean disableButton) {
        this.disableButton = disableButton;
    }

    public SearchParamBean getSearchParamBean() {
        return searchParamBean;
    }

    public void setSearchParamBean(SearchParamBean searchParamBean) {
        this.searchParamBean = searchParamBean;
    }
}


