package com.qrgeneratorapp.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by indkushwah on 5/27/2016.
 */
public class DBController {


    private AppDBHelper mDBHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DBController(Context context) {
        mContext = context;
        mDBHelper = new AppDBHelper(mContext);
    }

}

