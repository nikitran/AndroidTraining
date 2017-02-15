package com.example.nikitran.wk4_timesheetapp;

import java.util.Date;

/**
 * Created by nikitran on 2/14/17.
 */

public class Consultant {
    private String mName;
    private String mPhone;
    private String mEmail;
    private String mEmployeeID;
    private String mProjectSite;
    private String CDM;
    private Boolean mTrainingStatus;
    private Boolean mOnProject;
    private Date startDate;

    public Consultant() {
        this.mName = "";
        this.mPhone = "";
        this.mEmail = "";
        this.mEmployeeID = "";
        this.mProjectSite = "";
        this.CDM = "";
        this.mTrainingStatus = true;
        this.mOnProject = false;
        this.startDate = null;
    }

    public Consultant(String mName, String mPhone, String mEmail, String mEmployeeID,
                      String mProjectSite, String CDM, Boolean mTrainingStatus, Boolean mOnProject,
                      Date startDate) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.mEmployeeID = mEmployeeID;
        this.mProjectSite = mProjectSite;
        this.CDM = CDM;
        this.mTrainingStatus = mTrainingStatus;
        this.mOnProject = mOnProject;
        this.startDate = startDate;
    }

    //Getters:
    public String getmName() {
        return mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmEmployeeID() {
        return mEmployeeID;
    }

    public String getmProjectSite() {
        return mProjectSite;
    }

    public String getCDM() {
        return CDM;
    }

    public Boolean getmTrainingStatus() {
        return mTrainingStatus;
    }

    public Boolean getmOnProject() {
        return mOnProject;
    }

    public Date getStartDate() {
        return startDate;
    }

    // Setters:
    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmEmployeeID(String mEmployeeID) {
        this.mEmployeeID = mEmployeeID;
    }

    public void setmProjectSite(String mProjectSite) {
        this.mProjectSite = mProjectSite;
    }

    public void setCDM(String CDM) {
        this.CDM = CDM;
    }

    public void setmTrainingStatus(Boolean mTrainingStatus) {
        this.mTrainingStatus = mTrainingStatus;
    }

    public void setmOnProject(Boolean mOnProject) {
        this.mOnProject = mOnProject;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
