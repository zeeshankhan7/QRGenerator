package com.qrgeneratorapp.utils;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewTreeObserver;

import com.qrgeneratorapp.models.HospitalUser;

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
    public static void showSnackBar(View view, String message){
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
        }
}
