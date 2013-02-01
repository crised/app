package web;

import model.Ad;
import model.Picture;
import model.User;
import service.AdService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class GalleriaBean implements Serializable {

    private List<String> paths;
    private List<String> paths_tn;
    private List<String> allPaths;
    private List<Ad> adsByUser;
    private List<String> picsPathsByUser;

    private String selected; //selected from carousel


    @Inject
    Ad ad;

    @Inject
    User user;


    @Inject
    AdService adService;

    @Inject
    Logger log;

    @PostConstruct
    public void getAllPathsImages() {

        log.info("Galleria Bean Created");
        allPaths = adService.getAllImagePaths();
        selected = "none";


    }

    @PreDestroy
    public void destroy() {

        log.info("Galleria Bean Destroyed");

    }

    // Events from viewParam
    public void getPathImagebyAdId() {

        ad = adService.getAdById(ad.getId());
        paths = adService.getByIdImagePaths(ad.getId());
        paths_tn = Picture.getThumbnailspaths(paths);
    }



    // Events from viewParam
    public void getPathImagebyUserId() {

        adsByUser = adService.getAdsByUser(user);

        /*picsPathsByUser = new ArrayList<>();

        for (Ad ad : adsByUser) {
            for (Picture pic : ad.getPictureList()) {
                picsPathsByUser.add(pic.getPath());
            }
        }
        */
    }

    public List<String> getPathImageByAd(Ad ad) {

        picsPathsByUser = new ArrayList<>();

        for (Ad adFromList : adsByUser) {
            if (adFromList.equals(ad)) {
                for (Picture pic : adFromList.getPictureList()) {
                    picsPathsByUser.add(pic.getPath());
                }

            }
        }

        return picsPathsByUser;


    }


    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }


    public List<String> getAllPaths() {
        return allPaths;
    }

    public void setAllPaths(List<String> allPaths) {
        this.allPaths = allPaths;
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

    public List<String> getPaths_tn() {
        return paths_tn;
    }

    public void setPaths_tn(List<String> paths_tn) {
        this.paths_tn = paths_tn;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
