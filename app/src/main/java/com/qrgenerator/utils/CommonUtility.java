package com.qrgenerator.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.qrgenerator.models.Attendant;
import com.qrgenerator.models.Visitor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    public static boolean isValidUserModel(Attendant attendant) {
        boolean isValidUserModel= true;
//        if (attendant.getVisitorOrAttendantName() == null || attendant.getVisitorOrAttendantName().equals("") || attendant.getVisitorOrAttendantName().equals("NULL")) {
//            isValidUserModel=false;
//        } else if(attendant.getPatientName() == null || attendant.getPatientName().equals("") || attendant.getPatientName().equals("NULL")) {
//            isValidUserModel=false;
//        }else
        if(attendant.getPatientId() == null || attendant.getPatientId().equals("") || attendant.getPatientId().equals("NULL")) {
            isValidUserModel=false;
        }else if(attendant.getVisitorOrAttendantMobileNo() == null || attendant.getVisitorOrAttendantMobileNo().equals("") || attendant.getVisitorOrAttendantMobileNo().equals("NULL")) {
            isValidUserModel=false;
        }
        return isValidUserModel;
    }
//    public static int isValidVisitorModel(Visitor visitor) {
//        int isValidVisitorModel= -1;
//        if (visitor.getVisitorName() == null || visitor.getVisitorName().equals("") || visitor.getVisitorName().equals("NULL")) {
//            isValidVisitorModel=0;
//        } else if(visitor.getContactNumber() == null || visitor.getContactNumber().equals("") || visitor.getContactNumber().equals("NULL")) {
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

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
