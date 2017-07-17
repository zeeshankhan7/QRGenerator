package com.qrgeneratorapp.models;

/**
 * Created by inmkhan021 on 7/17/2017.
 */

public class Visitor {


    private String visitorName;
    private String visitorAddress;
    private String visitorContactNo;

    public Visitor(String visitorName, String visitorAddress, String visitorContactNo, boolean isAllowed) {
        this.visitorName = visitorName;
        this.visitorAddress = visitorAddress;
        this.visitorContactNo = visitorContactNo;
        this.isAllowed = isAllowed;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    private boolean isAllowed=false;

    public String getVisitorAddress() {
        return visitorAddress;
    }

    public void setVisitorAddress(String visitorAddress) {
        this.visitorAddress = visitorAddress;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorContactNo() {
        return visitorContactNo;
    }

    public void setVisitorContactNo(String visitorContactNo) {
        this.visitorContactNo = visitorContactNo;
    }
}
