package com.qrgenerator.models;

import java.io.Serializable;

/**
 * Created by inmkhan021 on 7/12/2017.
 */

public class Attendant implements Serializable {
//    private String patientName;
    private String patientId;
//    private String visitorOrAttendantName;
    private String visitorOrAttendantMobileNo;
//    public String getPatientName() {
//        return patientName;
//    }

//    public void setPatientName(String patientName) {
//        this.patientName = patientName;
//    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

//    public String getVisitorOrAttendantName() {
//        return visitorOrAttendantName;
//    }

//    public void setVisitorOrAttendantName(String visitorOrAttendantName) {
//        this.visitorOrAttendantName = visitorOrAttendantName;
//    }

    public String getVisitorOrAttendantMobileNo() {
        return visitorOrAttendantMobileNo;
    }

    public void setVisitorOrAttendantMobileNo(String visitorOrAttendantMobileNo) {
        this.visitorOrAttendantMobileNo = visitorOrAttendantMobileNo;
    }

    @Override
    public String toString() {
        return patientId+":"+visitorOrAttendantMobileNo;
    }
}
