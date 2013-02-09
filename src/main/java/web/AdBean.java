package web;

import exception.PictureException;
import model.Ad;
import model.User;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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

    private Boolean disabled;
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

        fileUpload(event.getFile());

    }

    public void fileUpload(UploadedFile file) {

        try {
            pictureUtil.createImage(file, ad);
            counter++;
            if (disabled) setDisabled(false);
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
            return null;

        }

        ad.setLatitude(mapBean.getLat());
        ad.setLongitude(mapBean.getLng());



        loggedUser = userService.findUser(
                FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());

        ad.setUser(loggedUser);
        ad = adService.createAd(ad); //Will not pass validation!
        disabled = true;
        counter = 0;
        //conversation.begin();

        return "/auth/next?faces-redirect=true&amp;includeViewParams=true";

    }


    public String finish() {

        conversation.end();
        return "/index?faces-redirect=true&amp;includeViewParams=true";

    }


    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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
