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
    private List<String> allPaths;
    private List<Ad> adsByUser;
    private Ad ad;
    private int adId;
    private List<String> picsPathsByUser;

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


    }

    @PreDestroy
    public void destroy() {

        log.info("Galleria Bean Destroyed");

    }

    // Events from viewParam
    public void getPathImagebyAdId() {

        ad = adService.getAdById(adId);
        paths = adService.getByIdImagePaths(adId);
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

        for(Ad adfromlist : adsByUser){
            if (adfromlist.equals(ad)){
                for(Picture pic : adfromlist.getPictureList()){
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

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
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
}
