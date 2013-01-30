package web;

import enums.City;
import enums.Region;
import util.Loggable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@Loggable
@RequestScoped
public class EnumBean {

    private List<Region> regions;
    private List<City> cities;

    @Inject
    private EnumBeanStatus enumBeanStatus;

    @Inject
    private Logger logger;


    @PostConstruct
    public void init(){

        regions = Region.getAllRegionsCleaned();
        cities = City.getAllCities(); // Load all at the beginning..
    }

    @PreDestroy
    public void predstroy(){
        logger.info("EnumBean2 is destroyed");
    }

    public void loadCities(){
        setCities(City.getCitiesByRegion(enumBeanStatus.getSelectedRegion()));
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        logger.info(this.cities.toString());

    }

}
