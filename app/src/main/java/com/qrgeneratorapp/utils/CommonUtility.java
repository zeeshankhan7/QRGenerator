package com.qrgeneratorapp.utils;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewTreeObserver;

import com.qrgeneratorapp.models.HospitalUser;
import com.qrgeneratorapp.models.Visitor;

/**
 * Created by inmkhan021 on 7/12/2017.
 */

public class CommonUtility {

    public static final String LOG_TAG = CommonUtility.class.getSimpleName();

    /*****
     * to check if string contains elements
     ****/
    public static boolean isStringEmptyOrNull(String inputStr) {
        if (inputStr == null || inputStr.equals("") || inputStr.equals("NULL")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isValidUserModel(HospitalUser hospitalUser) {
        boolean isValidUserModel= true;
        if (hospitalUser.getVisitorOrAttendantName() == null || hospitalUser.getVisitorOrAttendantName().equals("") || hospitalUser.getVisitorOrAttendantName().equals("NULL")) {
            isValidUserModel=false;
        } else if(hospitalUser.getPatientName() == null || hospitalUser.getPatientName().equals("") || hospitalUser.getPatientName().equals("NULL")) {
            isValidUserModel=false;
        }else if(hospitalUser.getPatientId() == null || hospitalUser.getPatientId().equals("") || hospitalUser.getPatientId().equals("NULL")) {
            isValidUserModel=false;
        }else if(hospitalUser.getVisitorOrAttendantMobileNo() == null || hospitalUser.getVisitorOrAttendantMobileNo().equals("") || hospitalUser.getVisitorOrAttendantMobileNo().equals("NULL")) {
            isValidUserModel=false;
        }
        return isValidUserModel;
    }
//    public static int isValidVisitorModel(Visitor visitor) {
//        int isValidVisitorModel= -1;
//        if (visitor.getVisitorName() == null || visitor.getVisitorName().equals("") || visitor.getVisitorName().equals("NULL")) {
//            isValidVisitorModel=0;
//        } else if(visitor.getVisitorMobileNo() == null || visitor.getVisitorMobileNo().equals("") || visitor.getVisitorMobileNo().equals("NULL")) {
//            isValidVisitorModel=1;
//        }
//        else if(visitor.getPatientName() == null || visitor.getPatientName().equals("") || visitor.getPatientName().equals("NULL")) {
//            isValidVisitorModel=2;
//        }else if(visitor.getPatientId() == null || visitor.getPatientId().equals("") || visitor.getPatientId().equals("NULL")) {
//            isValidVisitorModel=3;
//        }
//        return isValidVisitorModel;
//    }
    public static boolean isValidVisitorModel(Visitor visitor) {
        boolean isValidVisitorModel= true;
        if (visitor.getVisitorName() == null || visitor.getVisitorName().equals("") || visitor.getVisitorName().equals("NULL")) {
            isValidVisitorModel=false;
        } else if(visitor.getPatientName() == null || visitor.getPatientName().equals("") || visitor.getPatientName().equals("NULL")) {
            isValidVisitorModel=false;
        }else if(visitor.getPatientId() == null || visitor.getPatientId().equals("") || visitor.getPatientId().equals("NULL")) {
            isValidVisitorModel=false;
        }else if(visitor.getVisitorMobileNo() == null || visitor.getVisitorMobileNo().equals("") || visitor.getVisitorMobileNo().equals("NULL")) {
            isValidVisitorModel=false;
        }
        return isValidVisitorModel;
    }
    public static void showSnackBar(View view, String message){
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
        }
}
