package com.qrgenerator.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inmkhan021 on 7/25/2017.
 */

public class VisitorListResponse {

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<VisitorListModel> getVisiters() {
        return visiters;
    }

    public void setVisiters(List<VisitorListModel> visiters) {
        this.visiters = visiters;
    }

    @SerializedName("patientName")
    private String patientName;
    @SerializedName("visiters")
    private List<VisitorListModel> visiters = new ArrayList<VisitorListModel>();
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
