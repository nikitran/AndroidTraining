package com.example.nikitran.timesheetapp;

/**
 * Created by nikitran on 2/14/17.
 */

public class Employee {
    private static String mName, mPhone, mEmail, mCDM;


    public Employee() {
        this.mName = "";
        this.mPhone = "";
        this.mEmail = "";
        this.mCDM = "";
    }
    public Employee(String mName, String mPhone, String mEmail, String CDM) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.mCDM = CDM;
    }

    public static void setmName(String mName) {
        Employee.mName = mName;
    }

    public static void setmPhone(String mPhone) {
        Employee.mPhone = mPhone;
    }

    public static void setmEmail(String mEmail) {
        Employee.mEmail = mEmail;
    }

    public static void setmCDM(String mCDM) {
        Employee.mCDM = mCDM;
    }

    public static String getmName() {

        return mName;
    }

    public static String getmPhone() {
        return mPhone;
    }

    public static String getmEmail() {
        return mEmail;
    }

    public static String getmCDM() {
        return mCDM;
    }
}
