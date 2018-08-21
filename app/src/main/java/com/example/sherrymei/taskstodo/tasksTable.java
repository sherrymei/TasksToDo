package com.example.sherrymei.taskstodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by SHERRYMEI on 7/5/2018.
 */

public class tasksTable {

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public tasksTable (Context context, SQLiteDatabase database, DatabaseHelper databaseHelper){
        this.context = context;
        this.database = database;
        this.databaseHelper = databaseHelper;
        this.database = databaseHelper.getWritableDatabase();
    }

    public boolean addTask ( String task, String datetime, String repeat, String list) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TASK, task);
        values.put(DatabaseHelper.KEY_DATETIME, datetime);
        values.put(DatabaseHelper.KEY_REPEAT, repeat);
        values.put(DatabaseHelper.KEY_LIST, list);
        long a = database.insert(DatabaseHelper.TABLE, null, values);
        return a > 0;

    }

    public ArrayList<Task> getAll() {
        this.database = databaseHelper.getWritableDatabase();
        ArrayList<Task> list = new ArrayList<>();
        String[] values = new String[] {
                DatabaseHelper.KEY_ID, DatabaseHelper.KEY_TASK,
                DatabaseHelper.KEY_DATETIME,
                DatabaseHelper.KEY_REPEAT,DatabaseHelper.KEY_LIST
        };

        Cursor cursor = database.query(DatabaseHelper.TABLE, values, null, null, null, null, DatabaseHelper.KEY_DATETIME + " ASC");

        while (cursor.moveToNext()) {
            Task item = new Task();
            item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_ID)));
            item.setItem(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_TASK)));
            item.setDateTime(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_DATETIME)));
            list.add(item);
        }
        return list;
    }
}
