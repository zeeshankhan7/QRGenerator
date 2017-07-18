package com.qrgeneratorapp.models;

/**
 * Created by inmkhan021 on 7/17/2017.
 */

public class Visitor {


    private String visitorName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    private String patientName;
//    private String visitorAddress;
    private String visitorMobileNo;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    private String patientId;
    public Visitor() {

    }
    public Visitor(String visitorName, String patientName,String patientId, String visitorContactNo, boolean isAllowed) {
        this.visitorName = visitorName;
        this.patientId = patientId;
        this.visitorMobileNo = visitorContactNo;
        this.patientName=patientName;
        this.isAllowed = isAllowed;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    private boolean isAllowed=false;

//    public String getVisitorAddress() {
//        return visitorAddress;
//    }
//
//    public void setVisitorAddress(String visitorAddress) {
//        this.visitorAddress = visitorAddress;
//    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorMobileNo() {
        return visitorMobileNo;
    }

    public void setVisitorMobileNo(String visitorMobileNo) {
        this.visitorMobileNo = visitorMobileNo;
    }
}
