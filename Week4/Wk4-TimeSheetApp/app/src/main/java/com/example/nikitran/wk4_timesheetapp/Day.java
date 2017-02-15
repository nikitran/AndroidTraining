package com.example.nikitran.wk4_timesheetapp;

import java.util.Date;

/**
 * Created by nikitran on 2/15/17.
 */

public class Day {
    private Date mDate;
    private double mRegular;
    private double mPto;
    private double mHoliday;




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
