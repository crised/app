package web;

import model.Ad;
import model.Picture;
import model.User;
import service.AdService;
import service.PictureService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class GalleriaBean implements Serializable {


    private List<Ad> adsByUser;
    private List<String> picsPathsByUser;
    private List<Picture> pictureList;
    private List<Picture> pictureAll;
    private Picture pictureSelected; //selected from carousel


    @Inject
    Ad ad;

    @Inject
    User user;


    @Inject
    AdService adService;

    @Inject
    PictureService pictureService;

    @Inject
    Logger log;

    public GalleriaBean() {


        // selected = "constructed";
    }

    @PostConstruct
    public void getAllPathsImages() {

        log.info("Galleria Bean Created");
        pictureAll = adService.getListOfPicsAll();
        //selected = "none";


    }

    @PreDestroy
    public void destroy() {

        log.info("Galleria Bean Destroyed");

    }

    // Events from viewParam
    public void ListPicturesByAdId() {

        //ad = adService.getAdById(ad.getId());
        pictureList = adService.getListOfPicsbyAdId(ad.getId());

    }


    // Events from viewParam
    public void getPathImagebyUserId() {

        adsByUser = adService.getAdsByUser(user);


    }


    public void deleteImage() {
        log.info(pictureSelected.getPath());
        int i = 0;
        for (Picture p : pictureList) {

            if (p == pictureSelected) {
                pictureList.remove(i);
            }

            i++;
        }

        i=0;
        for (Picture p : pictureAll) {

            if(p == pictureSelected){
                pictureAll.remove(i);
            }
            i++;
        }

        pictureSelected.setRemoved(true);
        pictureService.updatePicture(pictureSelected);



    }


    //faces-redirect o update carousel

    public List<Ad> getAdsByUser() {
        return adsByUser;
    }

    public void setAdsByUser(List<Ad> adsByUser) {
        this.adsByUser = adsByUser;
    }

    public List<String> getPicsPathsByUser() {
        return picsPathsByUser;
    }

    public void setPicsPathsByUser(List<String> picsPathsByUser) {
        this.picsPathsByUser = picsPathsByUser;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public List<Picture> getPictureAll() {
        return pictureAll;
    }

    public void setPictureAll(List<Picture> pictureAll) {
        this.pictureAll = pictureAll;
    }

    public Picture getPictureSelected() {
        return pictureSelected;
    }

    public void setPictureSelected(Picture pictureSelected) {
        this.pictureSelected = pictureSelected;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
