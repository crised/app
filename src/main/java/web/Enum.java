package web;

import enums.City;
import enums.Price;
import enums.Region;
import enums.Surface;
import org.jboss.logging.Logger;

import javax.faces.bean.ManagedBean;

/**
 * Date: 2/15/13
 * Time: 2:24 PM
 */

@ManagedBean
public class Enum {


    private Price selectedPrice;
    private Surface selectedSurface;
    private City selectedCity;
    private Region selectedRegion;


    static final Logger log = Logger.getLogger(Enum.class);

    public Price[] getPriceArray() {
        return Price.values();
    }

    public Surface[] getSurfaceArray() {
        return Surface.values();
    }

    public City[] getCityArray() {
        if (selectedRegion == null) {
            return City.values();
        }
        return City.getCityArrayByRegion(this.selectedRegion);

    }

    public Region[] getRegionArray() {
        return Region.values();
    }



    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public Region getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(Region selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    public Price getSelectedPrice() {
        return selectedPrice;
    }

    public void setSelectedPrice(Price selectedPrice) {
        this.selectedPrice = selectedPrice;
    }

    public Surface getSelectedSurface() {
        return selectedSurface;
    }

    public void setSelectedSurface(Surface selectedSurface) {
        this.selectedSurface = selectedSurface;
    }
}
