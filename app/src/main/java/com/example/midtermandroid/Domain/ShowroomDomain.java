package com.example.midtermandroid.Domain;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowroomDomain {
    private double lat;
    private double lng;
    private String title;
    private String uriImage;

    public ShowroomDomain(){

    }

    public ShowroomDomain(ShowroomDomain other){
        this.lat = other.lat;
        this.lng = other.lng;
        this.title = other.title;
        this.uriImage = other.uriImage;
    }

    public ShowroomDomain(double lat, double lng, String title, String uriImage) {
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.uriImage = uriImage;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public MarkerOptions toMarkerOptions() {
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(this.lat, this.lng))
                .title(this.title);

        return marker;
    }
}
