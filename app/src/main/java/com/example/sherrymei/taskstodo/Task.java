package com.example.sherrymei.taskstodo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by SHERRYMEI on 7/29/2018.
 */

public class Task {

    private int id;
    private String item, month, dayWeek, time, ampm, repeat, list;
    private int dayMonth, year;
    private boolean isDone = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    public void setMonth(int month) {
        Calendar calendar = new GregorianCalendar(year, month, dayMonth);
        String monthString = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        this.month = monthString;
    }

    public void setDayMonth(int dayMonth) {
        this.dayMonth = dayMonth;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAmPm(String am_pm) {
        this.ampm = am_pm;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDateTime() {
        String dt = dayWeek + ", " + month + " " + dayMonth + ", " + year + " " + time + " " + ampm;
        return dt;
    }
}
