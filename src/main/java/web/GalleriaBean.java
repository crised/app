package web;

import model.Ad;
import service.AdService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class GalleriaBean implements Serializable {

    private List<String> paths;
    private List<String> allPaths;
    private Ad ad;
    private int adId;

    @Inject
    AdService adService;

    @Inject
    Logger log;

    @PostConstruct
    public void getAllPathsImages() {

        log.info("Galleria Bean Created");



    }

    @PreDestroy
    public void destroy() {

        log.info("Galleria Bean Destroyed");

    }

    // Events from viewParam
    public void getPathImagebyAdId() {

        ad = adService.getAdById(adId);
        allPaths = adService.getAllImagePaths();
        paths = adService.getByIdImagePaths(adId);
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
}
