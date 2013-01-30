package web;

import enums.City;
import enums.Region;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class EnumRegistration {

    private List<Region> regionList;
    private List<City> cityList;

    private Region selectedRegion;


    @PostConstruct
    public void init() {

        regionList = Region.getAllRealRegions();
        selectedRegion = Region.OHIGGINS;
        selectCity();
    }

    public void selectCity() {

        cityList = City.getRealCitiesByRegion(selectedRegion);

    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public Region getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(Region selectedRegion) {
        this.selectedRegion = selectedRegion;
    }
}
