package com.example.nikitran.wk4.timesheetapp;

import java.util.Date;

/**
 * Created by nikitran on 2/15/17.
 */

public class Day {
    private String mDOW;
    private Date mDate;
    private double mRegular;
    private double mPto;
    private double mHoliday;

    public Day() {
        this.mDate = null;
        this.mRegular = 0;
        this.mPto = 0;
        this.mHoliday = 0;
    }

    public Day(Date mDate, double mRegular) {
        this.mDate = mDate;
        this.mRegular = mRegular;
        this.mPto = 0;
        this.mHoliday = 0;
    }

    public void setmDOW(String mDOW) {
        this.mDOW = mDOW;
    }

    public String getmDOW() {

        return mDOW;
    }
    public Date getmDate() {
        return mDate;
    }

    public double getmRegular() {
        return mRegular;
    }

    public double getmPto() {
        return mPto;
    }

    public double getmHoliday() {
        return mHoliday;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmRegular(double mRegular) {
        this.mRegular = mRegular;
    }

    public void setmPto(double mPto) {
        this.mPto = mPto;
    }

    public void setmHoliday(double mHoliday) {
        this.mHoliday = mHoliday;
    }
}
