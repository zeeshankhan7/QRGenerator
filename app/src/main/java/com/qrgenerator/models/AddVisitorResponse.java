package com.qrgenerator.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public class AddVisitorResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;


    public AddVisitorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

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
