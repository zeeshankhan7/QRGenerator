package com.qrgenerator.models;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public class AddVisitorParams {
    private String visitorName;
    private String contactNumber;
    private String patientId;

    public AddVisitorParams(String visitorName, String visitorMobileNo, String patientId) {
        this.visitorName = visitorName;
        this.contactNumber = visitorMobileNo;
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
