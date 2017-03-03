
package com.example.nikitran.wk5_retrofit_rxjava_glide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Name implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("last")
    @Expose
    private String last;
    private final static long serialVersionUID = 9191087393216219911L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Name: " + title + " " + first + " " + last;
    }
}
