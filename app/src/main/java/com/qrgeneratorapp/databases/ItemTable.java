package com.qrgeneratorapp.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.qrgenerator.models.Visitor;

import java.util.ArrayList;

/**
 * Created by inmkhan021 on 7/20/2017.
 */
public class ItemTable implements ITable {


    public static final String TABLE_NAME = "visitor";

    public static final String COL_ID = "_id";
    public static final String COL_PATIENT_ID = "patient_id";
    public static final String COL_PATIENT_NAME = "patient_name";
    public static final String COL_VISITOR_NAME = "visitor_name";
    public static final String COL_VISITOR_MOBILE_NO = "visitor_mobile_no";
    public static final String COL_IS_VISITOR_ALLOWED = "visitor_allowed";
    // Creating table query
    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_PATIENT_ID + " TEXT, "  //TEXT NOT NULL
            + COL_PATIENT_NAME + " TEXT, "
            + COL_VISITOR_NAME + " TEXT, "
            + COL_VISITOR_MOBILE_NO + " TEXT, "
            + COL_IS_VISITOR_ALLOWED + " TEXT); ";


    AppDBHelper mAppDBHelper;

    public ItemTable(AppDBHelper appDBHelper) {
        mAppDBHelper = appDBHelper;
    }


    public void insert(Visitor model) {
        if (null == model) throw new IllegalArgumentException("Model Can't be Null");
        SQLiteDatabase database = mAppDBHelper.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(COL_VISITOR_NAME, model.getVisitorName());
        contentValue.put(COL_PATIENT_ID, model.getPatientId());
//        contentValue.put(COL_PATIENT_NAME, model.getPatientName());
        contentValue.put(COL_VISITOR_MOBILE_NO, model.getVisitorMobileNo());
        contentValue.put(COL_IS_VISITOR_ALLOWED, model.isAllowed());
        database.insert(TABLE_NAME, null, contentValue);
        database.close();
    }


    public void deleteAll() {
        SQLiteDatabase database = mAppDBHelper.getWritableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();

    }

    /*String[] projection = {
        FeedEntry._ID,
        FeedEntry.COLUMN_NAME_TITLE,
        FeedEntry.COLUMN_NAME_UPDATED,
        ...
        };

    // How you want the results sorted in the resulting Cursor
    String sortOrder =
        FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    Cursor c = db.query(
        FeedEntry.TABLE_NAME,  // The table to query
        projection,                               // The columns to return
        selection,                                // The columns for the WHERE clause
        selectionArgs,                            // The values for the WHERE clause
        null,                                     // don't group the rows
        null,                                     // don't filter by row groups
        sortOrder                                 // The sort order
        );
     */
    public ArrayList<Visitor> getAllData() {
        return findVisitor(null);
    }


    public ArrayList<Visitor> getAllVisitorOfPatientID(String patientID) {
        return findVisitor(COL_PATIENT_ID + "='" + patientID + "'");
    }


    private ArrayList<Visitor> findVisitor(String queryName) {
        SQLiteDatabase database = mAppDBHelper.getWritableDatabase();
        ArrayList<Visitor> data = new ArrayList<Visitor>();
        Cursor cursor = database.query(TABLE_NAME, null, queryName,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String visitorName = cursor.getString(cursor.getColumnIndex(COL_VISITOR_NAME));
                String patientID = cursor.getString(cursor.getColumnIndex(COL_PATIENT_ID));
                String patientName = cursor.getString(cursor.getColumnIndex(COL_PATIENT_NAME));
                String visitorMobileNo = cursor.getString(cursor.getColumnIndex(COL_VISITOR_MOBILE_NO));
                Visitor visitor = new Visitor();
                visitor.setVisitorName(visitorName);
//                visitor.setPatientName(patientName);
                visitor.setPatientId(patientID);
                visitor.setVisitorMobileNo(visitorMobileNo);
                data.add(visitor);
                cursor.moveToNext();
            }
        }
        database.close();
        ;
        return data;
    }





/*    public int update(long _id, CategoryMainModel model) {
        if (null == model) throw new IllegalArgumentException("Model Can't be Null");
        return update(_id, model.getName(), model.getDesc());
    }*/


    public void delete(long _id) {
        SQLiteDatabase database = mAppDBHelper.getWritableDatabase();
        database.delete(TABLE_NAME, COL_ID + "=" + _id, null);
        database.close();
    }

    @Override
    public String getCreateCommand() {
        return CREATE_TABLE;
    }



}
