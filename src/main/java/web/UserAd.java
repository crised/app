package web;

import model.Ad;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Date: 2/21/13
 * Time: 8:55 AM
 */

@Named
@SessionScoped
public class UserAd extends Messages implements Serializable {

    static final Logger log = Logger.getLogger(UserAd.class);

    @Inject
    CacheBean cacheBean;

    private String userId;

    private List<Ad> adList;

    private String adIdString;


    @PostConstruct
    public void preRender() {

        String loggedUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        adList = cacheBean.getAdsByUser(loggedUser);
    }

    public void softRemove() {

        try {
            Integer adId = new Integer(adIdString);
            Ad ad = cacheBean.getAd(adId);
            if (ad != null && ad.getRemoved() != Boolean.TRUE) {

                log.info(adList.remove(ad));
                ad.setRemoved(Boolean.TRUE);
                cacheBean.removeAd(ad); // Deleted from Cache & DB, takes time to get deleted
                addSimpleMessage(ad.getShortDescription());
                addSimpleMessage(rB.getString("userAd.removed"));
                log.info(adList.size());
            } else addSimpleMessage(rB.getString("error"));


        } catch (Exception e) {
            addSimpleMessage(rB.getString("error"));
        }


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

    public String getAdIdString() {
        return adIdString;
    }

    public void setAdIdString(String adIdString) {
        this.adIdString = adIdString;
    }
}