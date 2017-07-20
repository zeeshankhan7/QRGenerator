package com.qrgeneratorapp.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by inmkhan021 on 7/20/2017.
 */
public class AppDBHelper extends SQLiteOpenHelper {

    public AppDBHelper(Context context) {
        super(context, DBManifest.DB_NAME, null, DBManifest.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        int tableCount = DBManifest.CREATE_TABLES_COMMANDS.length ;
        for (int i = 0; i < tableCount; i++) {
            db.execSQL(DBManifest.CREATE_TABLES_COMMANDS[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       // int tableCount = DBManifest.CREATE_TABLES_COMMANDS.length;
        //for (int i = 0; i < tableCount; i++) {
        int tableCount = DBManifest.TABLES_NAME.length ;
        for (int i = 0; i < tableCount; i++) {
            db.execSQL("DROP TABLE IF EXISTS " + DBManifest.TABLES_NAME[i]);
        }
        //}
        onCreate(db);
    }
}