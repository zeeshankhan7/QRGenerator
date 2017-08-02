package com.qrgenerator.models;

import java.io.Serializable;

/**
 * Created by inmkhan021 on 7/12/2017.
 */

public class Attendant implements Serializable {
//    private String patientName;
    private String patientId;
//    private String visitorOrAttendantName;
    private String attendentContactNo;

    public Attendant(String patientId, String attendantContactNo) {
        this.patientId = patientId;
        this.attendentContactNo = attendantContactNo;
    }
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

    public String getAttendentContactNo() {
        return attendentContactNo;
    }

    public void setAttendentContactNo(String attendentContactNo) {
        this.attendentContactNo = attendentContactNo;
    }

    @Override
    public String toString() {
        return patientId+":"+ attendentContactNo;
    }
}
