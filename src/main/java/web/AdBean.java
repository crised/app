package web;

import enums.Region;
import exception.PictureException;
import model.Ad;
import model.User;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import service.AdService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class AdBean extends Messages implements Serializable {

    private Integer counter;

    static final Logger log = Logger.getLogger(AdBean.class);

    @Inject
    Ad ad;

    @Inject
    MapBean mapBean;

    @Inject
    AdService adService;

    @Inject
    PictureUtil pictureUtil;

    @Inject
    UserService userService;

    @Inject
    User loggedUser;

    @Inject
    Conversation conversation;





    public AdBean() {
        //ad = new Ad();
    }


    @PostConstruct
    public void postConstruct() {
        log.info("AdBean Created");
    }

    @PreDestroy
    public void destroy() {
        log.info("AdBean Destroyed");
    }

    public void handleFileUpload(FileUploadEvent event) {

        try {
            pictureUtil.createImage(event.getFile(), ad);
            counter++;
        } catch (PictureException e) {
            addSimpleMessage(e.getMessage());
        }
    }

    public String initConversation() {

        if (conversation.isTransient()) {
            conversation.begin();
        }
        return "/auth/addad?faces-redirect=true&amp;includeViewParams=true";
    }

    public String next() {
        if (mapBean.getLat() == 0 || mapBean.getLng() == 0) {
            addSimpleMessage(rB.getString("ad.mapBean.noPosition"));
            log.info(rB.getString("ad.mapBean.noPosition"));
            return null;
        }

        ad.setLatitude(mapBean.getLat());
        ad.setLongitude(mapBean.getLng());
        loggedUser = userService.findUser(
                FacesContext.getCurrentInstance().
                        getExternalContext().getRemoteUser());
        ad.setUser(loggedUser);
        ad = adService.createAd(ad); //Will not pass validation!
        counter = 0;
        return "/auth/next?faces-redirect=true&amp;includeViewParams=true";
    }


    public String finish() {

        if (counter.equals(0)) {
            addSimpleMessage(rB.getString("ad.uploadNoUpload"));
            return null;
        }

        ad.setPublished(Boolean.TRUE);
        adService.updateAd(ad);

        conversation.end();
        return "/auth/home?pub=true?faces-redirect=true";
    }


    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public MapBean getMapBean() {
        return mapBean;
    }

    public void setMapBean(MapBean mapBean) {
        this.mapBean = mapBean;
    }
}
