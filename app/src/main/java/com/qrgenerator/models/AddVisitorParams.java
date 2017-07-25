package com.qrgenerator.models;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public class AddVisitorParams {
    private String visiterName;
    private String contactNumber;
    private String patientId;

    public AddVisitorParams(String visitorName, String visitorMobileNo, String patientId) {
        this.visiterName = visitorName;
        this.contactNumber = visitorMobileNo;
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisiterName() {
        return visiterName;
    }

    public void setVisiterName(String visiterName) {
        this.visiterName = visiterName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
