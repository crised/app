package web;

import enums.City;
import enums.Price;
import enums.Surface;

import java.io.Serializable;

/**
 * Date: 2/17/13
 * Time: 4:05 PM
 */

public class SearchParamBean implements Serializable {

    private Price price;
    private Surface surface;
    private City city;
    private boolean waterRights;
    private boolean facilities;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isWaterRights() {
        return waterRights;
    }

    public void setWaterRights(boolean waterRights) {
        this.waterRights = waterRights;
    }

    public boolean isFacilities() {
        return facilities;
    }

    public void setFacilities(boolean facilities) {
        this.facilities = facilities;
    }
}
