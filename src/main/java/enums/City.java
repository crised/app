package enums;

import util.Constants;

import java.util.*;

public enum City {


    ALL(Region.ALL, Constants.Fixed.allCities, true), CITYREGIONS1(Region.TARAPACA, Constants.Fixed.citiesIn, true), IQUIQUE(Region.TARAPACA, "Iquique"),
    CITYREGIONS6(Region.OHIGGINS, Constants.Fixed.citiesIn, true), COINCO(Region.OHIGGINS, "Coinco"), COLTAUCO(Region.OHIGGINS, "Coltauco"),
    QUINTADETILCOCO(Region.OHIGGINS, "Quinta de Tilcoco");


    private String label;
    private Region region;
    private Boolean isGroup;



    private City(Region region, String label) {
        this.region = region;
        this.label = label;
    }

    private City(Region region, String label, Boolean isGroup) {
        this.region = region;
        this.label = label + region.getLabel();
        this.isGroup = isGroup;
    }

    public static City[] getCityArrayByRegion(Region region) {

        if(region == Region.ALL){
            return City.values();
        }

        List<City> citiesList = new ArrayList<City>();

        for(City city : City.values()){
            if(city.getRegion()==region){
                citiesList.add(city);
            }
        }

        City[] cities =  citiesList.toArray(new City[0]);
        return cities;

    }

    public static List<City> getAllCities() {

        List<City> cityList = new LinkedList<City>(); //LinkedHashSet los deja en el orden del ENUM, HashSet -> aleatorio
        //Collections.addAll(cityList, City.values());
        cityList.add(ALL);
        for (City c : City.values()) {
            if (c.isGroup == null) cityList.add(c);
        }
        return cityList;
    }

    public static List<City> getAllRealCities() {

        List<City> cityList = new LinkedList<City>(); //LinkedHashSet los deja en el orden del ENUM, HashSet -> aleatorio
        //Collections.addAll(cityList, City.values());
        for (City c : City.values()) {
            if (c.isGroup == null) cityList.add(c);
        }
        return cityList;
    }

    public static List<City> getAllGroups() {

        List<City> groupList = new LinkedList<City>(); //LinkedHashSet los deja en el orden del ENUM, HashSet -> aleatorio
        //Collections.addAll(cityList, City.values());
        for (City c : City.values()) {
            if (c.isGroup) groupList.add(c);
        }
        return groupList;
    }

    public static List<String> getAllCitiesByString() {

        List<City> cityList = getAllCities();
        List<String> cityListByString = new LinkedList<String>();

        for (City c : cityList) {
            cityListByString.add(c.toString());
        }

        Collections.sort(cityListByString); //optional alphabet
        return cityListByString;

    }

    public static List<City> getCitiesByRegion(Region region) {

        List<City> cityListByRegion = new LinkedList<City>();

        if (region == Region.ALL) {

            cityListByRegion = getAllCities();


        } else {
            for (City c : City.values()) {
                if (c.region == region) cityListByRegion.add(c);

            }
        }


        return cityListByRegion;
    }

    public static List<City> getRealCitiesByRegion(Region region) {

        List<City> cityListByRegion = new LinkedList<City>();

        if (region == Region.ALL) {

            cityListByRegion = getAllRealCities();


        } else {
            for (City c : City.values()) {
                if (c.region == region && c.isGroup == null){
                    cityListByRegion.add(c);
                }

            }
        }


        return cityListByRegion;
    }

    public static List<String> getCitiesByRegionByString(Region region) {

        List<City> cityListByRegion = getCitiesByRegion(region);
        List<String> cityListByRegionByString = new LinkedList<String>();

        for (City c : cityListByRegion) {

            cityListByRegionByString.add(c.toString());

        }

        return cityListByRegionByString;
    }

    public static Map<City, Region> getMapRegionCity() {

        Map<City, Region> regionCityMap = new HashMap<City, Region>();

        for (City c : City.values()) {

            regionCityMap.put(c, c.getRegion());
        }

        return regionCityMap;
    }

    public static Map<String, Integer> getMapRegionCityIntString() {

        Map<City, Region> rCMap = getMapRegionCity();
        Map<String, Integer> rcMapCityIntString = new HashMap<String, Integer>();

        for (Map.Entry<City, Region> entry : rCMap.entrySet()) {

            String key = entry.getKey().toString();
            Integer value = entry.getValue().ordinal();
            rcMapCityIntString.put(key, value);
        }

        return rcMapCityIntString;
    }

    public String getLabel() {
        return label;
    }

    public Region getRegion() {
        return region;
    }

    public Boolean getGroup() {
        return isGroup;
    }

    public void setGroup(Boolean group) {
        isGroup = group;
    }

    @Override
    public String toString() {
        return label;
    }
}
