
package com.example.nikitran.wk5_retrofit_rxjava_glide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Example implements Serializable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("info")
    @Expose
    private Info info;
    private final static long serialVersionUID = 1794563494447972791L;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Example{" +
                "results=" + results +
                ", info=" + info +
                '}';
    }
}