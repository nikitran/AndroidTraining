package com.example.nikitran.wk3_recyclerview_sqlite;

/**
 * Created by nikitran on 2/13/17.
 */

public class Item {
    private String mURL;
    private String mName;

    public Item(String mImgURL, String name) {
        this.mURL = mImgURL;
        this.mName = name;

    }

    public String getName() {
        return mName;
    }

    public String getmImgURL() {
        return mURL;
    }

    public void setmImgURL(String mImgURL) {
        this.mURL = mImgURL;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
