var currentMarker = null;

function handlePointClick(event) {


    if (currentMarker == null) {
        document.getElementById('lat').value = event.latLng.lat();
        document.getElementById('lng').value = event.latLng.lng();

        currentMarker = new google.maps.Marker({
            position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng())
        });

        map.addOverlay(currentMarker);

        dlg.show();
    }
}

function markerAddComplete() {
    dlg.hide();
}

function cancel() {
    dlg.hide();
    currentMarker.setMap(null);
    currentMarker = null;

    return false;
}
