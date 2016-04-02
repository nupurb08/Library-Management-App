package com.android.mysummonsearch.constant;

import com.android.mysummonsearch.CalendarNew;

public abstract class DBConstant
{
    //database file directory
    public static String DATABASE_PATH = "/data/data/"+ CalendarNew.packageName+"/databases";
    //database file name
    public static String DATABASE_FILE = "libcal.db";
    //database version
    public static int DATABASE_VERSION = 1;
}
