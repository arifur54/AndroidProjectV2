package com.quickonference.restaurantguide.helpers;

import com.google.android.gms.maps.model.LatLng;

public class PlaceInfo {
    String address;
    LatLng latlng;
    String sLatLng;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public String getsLatLng() {
        return sLatLng;
    }

    public void setsLatLng(String sLatLng) {
        this.sLatLng = sLatLng;
    }

    public PlaceInfo(String address, LatLng latlng) {
        this.address = address;
        this.latlng = latlng;
    }

    public PlaceInfo(String address, String sLatLng) {
        this.address = address;
        this.sLatLng = sLatLng;
    }

}
