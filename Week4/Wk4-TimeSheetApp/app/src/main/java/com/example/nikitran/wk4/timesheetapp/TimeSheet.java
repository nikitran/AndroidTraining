package com.example.nikitran.wk4.timesheetapp;

import java.util.Date;
import java.util.List;

/**
 * Created by nikitran on 2/14/17.
 */


public class TimeSheet {
    public static final int DAYS_OF_WEEK = 7;

    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }

    private Employee account;
    private Date startDate;
    private Date endDate;
    private List<Day> Week;

    public void setDay(int thisDay, Day day)
    {
        Week.add(Week.indexOf(thisDay), day);
    }


}
