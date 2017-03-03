
package com.example.nikitran.wk5_retrofit_rxjava_glide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Id implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private Object value;
    private final static long serialVersionUID = 3358028608497878721L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Id{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
