package web;

import model.Ad;
import model.User;
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
import java.util.logging.Logger;

@Named
@ConversationScoped
public class AdBean implements Serializable {

    private Boolean disabled;
    private Integer counter;

    @Inject
    Ad ad;

    @Inject
    Logger log;

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

        pictureUtil.createImage(file, ad);
        counter++;
        if (disabled) setDisabled(false);

    }

    public String initConversation() {

        if(conversation.isTransient()){
        conversation.begin();
        }

        return "/auth/addad?faces-redirect=true&amp;includeViewParams=true";

    }


    public String next() {


        loggedUser = userService.findUser(
                FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());

        ad.setUser(loggedUser);
        //ad = adService.createAd(ad); Will not pass validation!
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
}
