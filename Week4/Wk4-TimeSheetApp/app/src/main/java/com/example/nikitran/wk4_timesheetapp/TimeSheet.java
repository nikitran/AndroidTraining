package com.example.nikitran.wk4_timesheetapp;

import java.util.Date;

/**
 * Created by nikitran on 2/14/17.
 */


public class TimeSheet {
    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }

    private Consultant account;
    private Date startDate;
    private Date endDate;

    private double[] RegularHours;
    private double[] PtoHours;
    private double [] HolidayHours;

    private double TotalHours;
}
