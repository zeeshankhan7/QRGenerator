package com.qrgenerator.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.qrgenerator.models.Attendant;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public class AppSharedPreferenceHelper {
    private static AppSharedPreferenceHelper appPreference;
    private SharedPreferences sharedPreferences;
    public static AppSharedPreferenceHelper getInstance(Context context) {
        if (appPreference == null) {
            appPreference = new AppSharedPreferenceHelper(context);
        }
        return appPreference;
    }
    private AppSharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.APP_PREF,Context.MODE_PRIVATE);
    }
    public void saveAttendant(Attendant attendant){

        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String json = gson.toJson(attendant); // myObject - instance of MyObject
        prefsEditor.putString(Constants.APP_PREF_KEY, json);
        prefsEditor.commit();
    }
    public Attendant getAttendant(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Constants.APP_PREF_KEY, "");
        Attendant obj = gson.fromJson(json, Attendant.class);
        return obj;
    }
    public String getPatientIDFromSP(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Constants.APP_PREF_KEY, "");
        Attendant obj = gson.fromJson(json, Attendant.class);
        String patientId=obj.getPatientId();
        return patientId;
    }
}
