package com.harishjose.myitemdelivery.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class Item implements Serializable{
    @SerializedName("description")
    private String description;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("location")
    private Location location;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
