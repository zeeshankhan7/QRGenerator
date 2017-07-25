package com.qrgenerator.models;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public class GetVisitorParams {
    private String patientId;

    public GetVisitorParams(String patientId) {

        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


}
