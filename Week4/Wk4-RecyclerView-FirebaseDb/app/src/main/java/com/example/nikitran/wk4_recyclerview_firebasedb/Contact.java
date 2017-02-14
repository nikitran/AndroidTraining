package com.example.nikitran.wk4_recyclerview_firebasedb;

/**
 * Created by nikitran on 2/13/17.
 */

public class Contact {
    private String mName;
    private String mPhone;
    private String key;

    public Contact() {
        mName = "";
        mPhone = "";
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getmName() {
        return mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public Contact getContact() {
        return this;
    }
}
