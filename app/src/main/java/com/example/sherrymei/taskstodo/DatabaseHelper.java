package com.example.sherrymei.taskstodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHERRYMEI on 6/6/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "SHERRY";

    public static final String TABLE = " TASKS ";
    public static final String KEY_ID = " _id ";
    public static final String KEY_TASK = "Task";
    public static final String KEY_DATE = "Date";
    public static final String KEY_TIME = "Time";
    public static final String KEY_REPEAT = "Repeat";
    public static final String KEY_LIST = "List";

    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE + " ( " +
                    KEY_ID          + " INTEGER PRIMARY KEY autoincrement, " +
                    KEY_TASK       + " TEXT, " +
                    KEY_DATE         + " FLOAT, " +
                    KEY_TIME        + " FLOAT, " +
                    KEY_REPEAT        + " TEXT, " +
                    KEY_LIST        + " TEXT) ";


    private static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
