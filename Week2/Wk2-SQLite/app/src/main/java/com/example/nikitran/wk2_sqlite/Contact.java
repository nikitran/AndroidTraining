package com.example.nikitran.wk2_sqlite;

/**
 * Created by nikitran on 1/30/17.
 */

public class Contact {
    //private variables
    private int mId;
    private String mName;
    private String mPhone;
    private static int count;

    //Empty constructor
    public Contact(){
    }

    //Constructors
    public Contact(int id, String name, String phone)
    {
        mId = id;
        mName = name;
        mPhone = phone;
    }

    public Contact(String name, String phone)
    {
        mName = name;
        mPhone = phone;
    }

    //getter & setter methods for id
    public int getID() {
        return mId;
    }
    public void setID(int id) {
        mId = id;
    }

    //getter & setter methods for name
    public String getName(){
        return mName;
    }
    public void setName(String name){
        mName = name;
    }

    //getter & setter methods for phone
    public String getPhone()
    {
        return mPhone;
    }
    public void setPhone(String phone){
        mPhone = phone;
    }

    //return the count
    public int ContactCount(){
        return count;
    }
}
