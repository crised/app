package web;

import model.Ad;
import model.Picture;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Date: 2/19/13
 * Time: 4:54 PM
 */

@RequestScoped
@Named
public class SingleAd {

    @Inject
    CacheBean cacheBean;

    @Inject
    MapBeanSimple mapBeanSimple;

    private int id;

    private Ad ad;

    private List<Picture> pictureList;


    public void preRender() {

        ad = cacheBean.getAd(id);
        if (ad != null) {
            pictureList = ad.getPictureList();
            mapBeanSimple.addMarker(ad.getLatitude(), ad.getLongitude());
        }


    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MapBeanSimple getMapBeanSimple() {
        return mapBeanSimple;
    }

    public void setMapBeanSimple(MapBeanSimple mapBeanSimple) {
        this.mapBeanSimple = mapBeanSimple;
    }
}
