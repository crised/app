package web;

import enums.City;
import enums.Price;
import enums.Region;
import enums.Surface;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Date: 2/15/13
 * Time: 2:24 PM
 */

@Named
@RequestScoped
public class Enum {



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

    public Region getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(Region selectedRegion) {
        this.selectedRegion = selectedRegion;
    }
}
