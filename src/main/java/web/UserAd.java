package web;

import model.Ad;
import org.jboss.logging.Logger;
import service.AdService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2/21/13
 * Time: 8:55 AM
 */

@Named
@RequestScoped
public class UserAd extends Messages implements Serializable {

    static final Logger log = Logger.getLogger(UserAd.class);

    @Inject
    CacheBean cacheBean;

    @Inject
    AdService adService;

    private String userId;

    private List<Ad> adList;

    private Integer adId;

    List<String> sampleList;

    @PostConstruct
    public void init(){
        sampleList = new ArrayList<String>();
        sampleList.add("1");
        sampleList.add("2");
        sampleList.add("3");
    }


    //@PostConstruct
    public void preRender() {

        if (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()
                .equals(userId)) //logged user must be the same as the requesting user
            adList = cacheBean.getAdsByUser(userId);
    }

    public void hello(String h){

        log.info(h);

    }

    public String softRemove() {

        Ad ad;
        if (adId != null)
            ad = cacheBean.cache.get(adId);
        else ad = null;

        if (ad != null && ad.getRemoved() != Boolean.TRUE) {

            ad.setRemoved(Boolean.TRUE);
            adService.updateAd(ad); //Deleted from DB
            cacheBean.removeAd(ad); // Deleted from Cache
            addSimpleMessage(ad.getShortDescription());
            addSimpleMessage(rB.getString("userAd.removed"));

        }

        return null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public List<String> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<String> sampleList) {
        this.sampleList = sampleList;
    }
}