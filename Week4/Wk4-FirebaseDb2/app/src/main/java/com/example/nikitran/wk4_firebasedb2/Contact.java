package com.example.nikitran.wk4_firebasedb2;

/**
 * Created by nikitran on 2/13/17.
 */

public class Contact {
    private String Name;
    private String Phone;
    private String Key;

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getKey() {
        return Key;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setKey(String key) {
        Key = key;
    }
}
