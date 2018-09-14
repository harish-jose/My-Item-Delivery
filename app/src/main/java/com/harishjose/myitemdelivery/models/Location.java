package com.harishjose.myitemdelivery.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class Location implements Serializable{
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("address")
    private String address;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
