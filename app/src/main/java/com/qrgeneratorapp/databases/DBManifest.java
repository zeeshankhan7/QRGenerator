package com.qrgeneratorapp.databases;

/**
 * Created by inmkhan021 on 7/20/2017.
 */
public class DBManifest {


    // Database Information
    public static final String DB_NAME = "max_hospital.db";
    // database version
    public static final int DB_VERSION = 8;

  //tables
  public static final String[] TABLES_NAME = {ItemTable.TABLE_NAME};
    public static final String[] CREATE_TABLES_COMMANDS = {ItemTable.CREATE_TABLE};


}
