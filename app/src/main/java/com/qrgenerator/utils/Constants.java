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
    public static final String[] VISITOR_NAME= {"Zeeshan", "Ram"};
    public static final String[] VISITOR_ADDRESS= {"Noida", "Gurgaon"};
    public static final String[] VISITOR_CONTACT_NO= {"9873799571", "7838036966"};
    public static final boolean[] VISITOR_ALLOWED= {true, true};
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 1000 * 60 * 1;// 1 min.
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 1000 * 60 * 1;// 1 min.
}
