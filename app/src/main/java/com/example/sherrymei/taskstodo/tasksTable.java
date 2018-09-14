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

    public boolean addTask (String task, String dayWeek, String month, String dayMonth, String year,
                             String time, String amPM, String repeat, String list) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TASK, task);
        values.put(DatabaseHelper.KEY_DAY_WEEK, dayWeek);
        values.put(DatabaseHelper.KEY_MONTH,month);
        values.put(DatabaseHelper.KEY_DAY_MONTH,dayMonth);
        values.put(DatabaseHelper.KEY_YEAR,year);
        values.put(DatabaseHelper.KEY_TIME,time);
        values.put(DatabaseHelper.KEY_AM_PM,amPM);
        values.put(DatabaseHelper.KEY_REPEAT, repeat);
        values.put(DatabaseHelper.KEY_LIST, list);
        long a = database.insert(DatabaseHelper.TABLE, null, values);
        return a > 0;

    }

    public ArrayList<Task> getAll() {
        this.database = databaseHelper.getWritableDatabase();
        ArrayList<Task> list = new ArrayList<>();
        String[] values = new String[] {
                DatabaseHelper.KEY_ID,DatabaseHelper.KEY_TASK, DatabaseHelper.KEY_DAY_WEEK,
                DatabaseHelper.KEY_MONTH, DatabaseHelper.KEY_DAY_MONTH, DatabaseHelper.KEY_YEAR,
                DatabaseHelper.KEY_TIME, DatabaseHelper.KEY_AM_PM,DatabaseHelper.KEY_REPEAT,
                DatabaseHelper.KEY_LIST
        };

        Cursor cursor = database.query(DatabaseHelper.TABLE, values, null, null, null, null,
                DatabaseHelper.KEY_MONTH      + ", " +
                DatabaseHelper.KEY_DAY_MONTH  + ", " +
                DatabaseHelper.KEY_YEAR       + ", " +
                DatabaseHelper.KEY_AM_PM      + ", " +
                DatabaseHelper.KEY_TIME       );

        while (cursor.moveToNext()) {
            Task item = new Task();
            item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_ID)));
            item.setItem(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_TASK)));
            item.setDayWeek(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_DAY_WEEK)));
            item.setMonth(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_MONTH)));
            item.setDayMonth(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_DAY_MONTH)));
            item.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_YEAR)));
            item.setTime(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_TIME)));
            item.setAmPm(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_AM_PM)));
            list.add(item);
        }
        return list;
    }
}
