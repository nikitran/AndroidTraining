package com.example.nikitran.wk4_firebasedb;

/**
 * Created by nikitran on 2/13/17.
 */

public class Contact {
    private String mName;
    private String mPhone;
    private String mKey;

    public Contact() {
    }
    public void setContact(String name, String phone) {
        this.mName = name;
        this.mPhone = phone;
    }
    public String getKey() {
        return mKey;
    }
    public void setKey(String key) {
        this.mKey = key;
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
