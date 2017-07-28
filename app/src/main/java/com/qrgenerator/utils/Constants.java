package com.qrgenerator.utils;

import com.qrgeneratorapp.max.R;

/**
 * Created by inmkhan021 on 7/12/2017.
 */

public class Constants {

    public static final String REQUEST_MESSAGE= " You can't leave the field empty ";
    public static final int[] tabIcons = {
            R.mipmap.ic_instruction,
            R.mipmap.ic_guide,
            R.mipmap.ic_qr_code,
            R.mipmap.ic_visitor
    };
    public static final String INSTRUCTION_TAB= "Instructions";
    public static final String GUIDE_TAB= "Guide";
    public static final String QR_CODE_TAB= "QR Code";
    public static final String VISITOR_TAB= "Visitor";
//    public static final String[] VISITOR_NAME= {"Zeeshan", "Ram"};
    public static final String[] VISITOR_ADDRESS= {"Noida", "Gurgaon"};
    public static final String[] VISITOR_CONTACT_NO= {"9873799571", "7838036966"};
    public static final boolean[] VISITOR_ALLOWED= {true, true};
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 1000 * 60 * 1;// 1 min.
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 1000 * 60 * 1;// 1 min.
    public static final String PATIENT_ID="patientId";
    public static final String VISITOR_NAME="visiterName";
    public static final String CONTACT_NUMBER="contactNumber";
    public static final String BASE_URL="http://demo-ramnath.rhcloud.com/";  //"https://reqres.in" //"http://demo-ramnath.rhcloud.com"
    public static final String ADD_VISITOR_URL= BASE_URL+"addVisitors.do";
    public static final String GET_VISITOR_LIST_URL=BASE_URL+"getVisitors.do";
    public static final String APP_PREF ="max_shared_pref" ;
    public static final String APP_PREF_KEY ="attendant_obj" ;
    public static final String RESPONSE_SUCCESS="success";
    public static final String RESPONSE_FAILURE="failure";
    public static final String RESPONSE_MESSAGE="Patient Id is not valid.";
    public static final String CONTENT_TYPE="application/json";
}
