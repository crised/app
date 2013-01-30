package web;

import enums.City;
import enums.Region;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class EnumBeanStatus {

    private Region selectedRegion;
    private City selectedCity;

    public String navigate(){
        return "EnumResult";  //enumresult?faces-redirect=true
    }

    public Region getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(Region selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }
}
