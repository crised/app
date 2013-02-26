package enums;

import javax.faces.model.SelectItem;
import java.util.*;

public enum Region {

    ALL("Todas", "All"), ARICA("Arica", "XV"), TARAPACA("Tarapacá", "I"),
    ANTOFAGASTA("Antofagasta", "II"), ATACAMA("Atacama", "III"), COQUIMBO("Coquimbo", "IV"),
    VALPARAISO("Valparaíso", "V"), OHIGGINS("O'Higgins", "VI"), MAULE("Maule", "VII"),
    BIOBIO("Biobío", "VIII"), ARAUCANIA("Araucanía", "IX"), RIOS("Ríos", "XIV"),
    LAGOS("Lagos", "X"), AYSEN("Aysén", "XI"), MAGALLANES("Magallanes", "XII"),
    METROPOLITANA("Metropolitana", "RM");


    private String label;
    private String romanNumber;

    private Region(String label, String romanNumber) {

        this.label = label;
        this.romanNumber = romanNumber;
    }

    public static Map<String, String> getMapAllRegions() {

        Map<String, String> map = new LinkedHashMap<>();

        for (Region entry : Region.values()) {

            if (entry.getLabel() != null) {
                Integer ordinal = (Integer) entry.ordinal();
                map.put(entry.toString(), ordinal.toString());
            }
        }

        return map;
    }

    public static List<Region> getAllRegions() {

        List<Region> regionList = new ArrayList<>();
        Collections.addAll(regionList, Region.values());
        return regionList;
    }

    public static List<Region> getAllRealRegions() {

        List<Region> regionList = new ArrayList<>();
        Collections.addAll(regionList, Region.values());
        regionList.remove(0);
        return regionList;
    }

    public static List<Region> getAllRegionsCleaned() {

        List<Region> regionList = new ArrayList<>();
        Collections.addAll(regionList, Region.values());
        return regionList;
    }

    public static List<String> getAllRegionsByString() {

        List<Region> regionList = getAllRegions();
        List<String> regionListByString = new ArrayList<>();

        for (Region r : regionList) {
            regionListByString.add(r.toString());
        }


        regionListByString.remove(0);
        Collections.sort(regionListByString);
        return regionListByString;

    }

    public static List<SelectItem> getAllRegionsBySelectItem() {

        List<Region> regionList = getAllRegions();
        List<SelectItem> regionSelectItemList = new ArrayList<SelectItem>();

        for (Region r : regionList) {

            SelectItem si = new SelectItem(r);
            regionSelectItemList.add(si);

        }

        return regionSelectItemList;
    }


    public String getLabel() {
        return label;
    }

    public String getRomanNumber() {
        return romanNumber;
    }

    @Override
    public String toString() {
        return label;
    }

}
