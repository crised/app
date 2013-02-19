package web;

import org.jboss.logging.Logger;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Date: 2/19/13
 * Time: 5:22 PM
 */

public class MapBeanSimple {

    static final Logger log = Logger.getLogger(MapBean.class);

    private MapModel simpleModel;

    private String mapCentered;

    public MapBeanSimple() {
        simpleModel = new DefaultMapModel();
    }

    public void addMarker(double lat, double lng){
        Marker marker = new Marker(new LatLng(lat,lng));
        simpleModel.addOverlay(marker);
        mapCentered = lat + ", " + lng;

    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public String getMapCentered() {
        return mapCentered;
    }

    public void setMapCentered(String mapCentered) {
        this.mapCentered = mapCentered;
    }
}
