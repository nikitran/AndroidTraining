
package com.example.nikitran.wk5_retrofit_rxjava_glide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Picture implements Serializable
{

    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    private final static long serialVersionUID = 2172169254749721087L;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
