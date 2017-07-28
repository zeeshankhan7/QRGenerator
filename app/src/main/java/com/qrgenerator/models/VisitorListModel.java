package com.qrgenerator.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inmkhan021 on 7/25/2017.
 */

public class VisitorListModel {
    @SerializedName("visiterName")
    private String visiterName;

    public VisitorListModel(String visiterName, String contactNumber) {
        this.visiterName = visiterName;
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getVisiterName() {
        return visiterName;
    }

    public void setVisiterName(String visiterName) {
        this.visiterName = visiterName;
    }
    @SerializedName("contactNumber")
    private String contactNumber;

}
