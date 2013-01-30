package web;

import model.Ad;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import service.AdService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ConversationScoped
public class AdBean implements Serializable {

    private Ad ad;
    private Boolean disabled;
    private Integer counter;

    @Inject
    Logger log;

    @Inject
    AdService adService;

    @Inject
    GalleriaBean galleriaBean;

    @Inject
    PictureUtil pictureUtil;

    @Inject
    Conversation conversation;


    public AdBean() {
        ad = new Ad();
    }


    @PostConstruct
    public void init() {
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


    public String finish() {

        conversation.end();
        galleriaBean.setAdId(ad.getId()); //ViewParam will be taken from this object
        return "imview?faces-redirect=true&amp;includeViewParams=true";
        //return "imview?aid=" + ad.getId() + "?faces-redirect=true";

    }


    public String next() {

        //updateAd(shortDescription, longDescription, price, city); //En este method se persiste el Ad..
        ad = adService.createAd(ad);
        log.info(ad.getPrice().toString());
        disabled = true;
        counter = 0;
        conversation.begin();

        return "next?faces-redirect=true&amp;includeViewParams=true";
        //return "imview?faces-redirect=true&amp;includeViewParams=true";

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
