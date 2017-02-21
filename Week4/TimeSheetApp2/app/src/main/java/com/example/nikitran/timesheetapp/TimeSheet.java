package com.example.nikitran.timesheetapp;

import java.util.Date;
import java.util.List;

/**
 * Created by nikitran on 2/17/17.
 */

public class TimeSheet {
    private Employee mEmployee;
    private List<Day> PayPeriod;
    private Date StartDate;

    private String key;

    public Employee getmEmployee() {
        return mEmployee;
    }

    public List<Day> getPayPeriod() {
        return PayPeriod;
    }

    public String getKey() {
        return key;
    }

    public void setmEmployee(Employee mEmployee) {
        this.mEmployee = mEmployee;
    }

    public void setPayPeriod(List<Day> payPeriod) {
        PayPeriod = payPeriod;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setHoursOfDay(int index, Day day)
    {
        PayPeriod.add(index, day);
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getStartDate() {

        return StartDate;
    }
}
