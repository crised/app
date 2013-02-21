package web;

import org.jboss.logging.Logger;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

/**
 * Date: 2/6/13
 * Time: 1:35 PM
 */


public class MapBean implements Serializable {

    static final Logger log = Logger.getLogger(MapBean.class);

    private MapModel emptyModel;

    private double lat;

    private double lng;

    private String onPointClick;

    private String mapCentered;

    public MapBean() {
        emptyModel = new DefaultMapModel();
    }


    @PostConstruct
    public void postConstruct() {
        log.info("MapBean Created");
        onPointClick = "handlePointClick(event);";
        mapCentered = "-33.43, -70.64";
    }

    @PreDestroy
    public void preDestroy() {
        log.info("MapBean Destroyed");
    }


    public void addMarker(ActionEvent actionEvent) {
        Marker marker = new Marker(new LatLng(lat, lng));
        marker.setDraggable(true);
        //marker.setTitle();
        emptyModel.addOverlay(marker);
        onPointClick = null;
        mapCentered = lat + ", " + lng;

        log.info(emptyModel.getMarkers().get(0).getLatlng().toString());


        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Ubicacion Seleccionada", "Puede arrastrar el marcador"));
    }

    public void onMarkerDrag(MarkerDragEvent event) {

        Marker marker = event.getMarker();
        lat = marker.getLatlng().getLat();
        lng = marker.getLatlng().getLng();
        log.info(marker.getLatlng());
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Nueva Ubicacion Seleccionada", "Esta sera la ubicacion definitiva"));
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getOnPointClick() {
        return onPointClick;
    }

    public void setOnPointClick(String onPointClick) {
        this.onPointClick = onPointClick;
    }

    public String getMapCentered() {
        return mapCentered;
    }

    public void setMapCentered(String mapCentered) {
        this.mapCentered = mapCentered;
    }
}