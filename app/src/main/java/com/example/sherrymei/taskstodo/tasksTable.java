package com.example.sherrymei.taskstodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SHERRYMEI on 7/5/2018.
 */

public class tasksTable {

    private final Context context;
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public tasksTable (Context context, SQLiteDatabase database, DatabaseHelper databaseHelper) {
        this.context = context;
        this.database = database;
        this.databaseHelper = databaseHelper;
        this.database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        if (databaseHelper != null) databaseHelper.close();
    }

    public void addTask(String task){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TASK, task);
    }
}
