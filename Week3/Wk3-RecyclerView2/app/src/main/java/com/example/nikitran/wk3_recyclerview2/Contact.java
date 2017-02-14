package com.example.nikitran.wk3_recyclerview2;

/**
 * Created by nikitran on 2/13/17.
 */

public class Contact {
    private String mName;
    private String mPhone;

    public Contact() {
    }

    public Contact(String mName, String mPhone) {
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
