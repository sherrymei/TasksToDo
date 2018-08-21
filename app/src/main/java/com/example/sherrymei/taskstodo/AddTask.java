package com.example.sherrymei.taskstodo;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddTask extends AppCompatActivity {

    public EditText taskTxt, dPicker, tPicker;
    public tasksTable table;
    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        SQLiteDatabase sqLiteDatabase;
        DatabaseHelper databaseHelper;
        try {
            sqLiteDatabase = openOrCreateDatabase(DatabaseHelper.DATABASE_NAME, MODE_PRIVATE, null);
            databaseHelper = new DatabaseHelper(this);
            table = new tasksTable(this,sqLiteDatabase,databaseHelper);

        }
        catch(SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        taskTxt = (EditText) findViewById(R.id.editText1);
        dPicker = (EditText) findViewById(R.id.editText2);
        tPicker = (EditText) findViewById(R.id.editText3);
        spinner = (Spinner) findViewById(R.id.spinner);


        dPicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddTask.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                        String monthString;
                        switch (selectedMonth) {
                            case 0:
                                monthString = "January";
                                break;
                            case 1:
                                monthString = "February";
                                break;
                            case 2:
                                monthString = "March";
                                break;
                            case 3:
                                monthString = "April";
                                break;
                            case 4:
                                monthString = "May";
                                break;
                            case 5:
                                monthString = "June";
                                break;
                            case 6:
                                monthString = "July";
                                break;
                            case 7:
                                monthString = "August";
                                break;
                            case 8:
                                monthString = "September";
                                break;
                            case 9:
                                monthString = "October";
                                break;
                            case 10:
                                monthString = "November";
                                break;
                            case 11:
                                monthString = "December";
                                break;
                            default:
                                monthString = "Invalid month";
                                break;
                        }

                        Calendar calendar = new GregorianCalendar(selectedYear, selectedMonth, selectedDay-1);
                        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                        String dayString;
                        switch (dayOfWeek) {
                            case 1:
                                dayString = "Monday";
                                break;
                            case 2:
                                dayString = "Tuesday";
                                break;
                            case 3:
                                dayString = "Wednesday";
                                break;
                            case 4:
                                dayString = "Thursday";
                                break;
                            case 5:
                                dayString = "Friday";
                                break;
                            case 6:
                                dayString = "Saturday";
                                break;
                            case 7:
                                dayString = "Sunday";
                                break;
                            default:
                                dayString = "Invalid day";
                                break;
                        }

                        String dateString = (dayString + ", " + monthString + " " + selectedDay + ", " + selectedYear);
                        dPicker.setText(dateString);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

        tPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(AddTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet (TimePicker timePicker,int selectedHour, int selectedMinute){
                        String am_pm;
                        if (selectedHour >= 12) am_pm = "PM";
                        else am_pm = "AM";
                        if (selectedHour > 12) selectedHour = selectedHour%12;
                        String timeString = String.format("%02d:%02d", selectedHour, selectedMinute) + " " + am_pm;
                        tPicker.setText(timeString);
                    }
                },hour,minute, false);
                mTimePicker.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tasks_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void onClick(View view) {
        //// TODO: store time as 24 hour
        //// TODO: make tasks repeatable
          // have repeat an alert box to repeat task
        //// TODO: make user create own lists and also add to their own list
/*
*         https://stackoverflow.com/questions/41927892/how-to-order-by-date-time-with-am-pm-in-sqlite
*         SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mma");
        Date date=new Date();
        try {
            date = parseFormat.parse(AmPmTimeFormatColumn);
        }catch (Exception e){}
        values.put("24HourFormatColumn", displayFormat.format(date));
*
*/

        String taskName = taskTxt.getText().toString();
        String datetext = dPicker.getText().toString();
        String timetext = tPicker.getText().toString();
        String repeat = spinner.getSelectedItem().toString();
        String list = "Default";

        String pattern1 = "EEEE, MMMM dd, yyyy HH:mm a";
        String pattern2 = "yyyy-MM-dd HH:mm:ss.sss"; //YYYY-MM-DD HH:MM:SS.SSS

        SimpleDateFormat format1 = new SimpleDateFormat(pattern1,Locale.US);
        SimpleDateFormat format2 = new SimpleDateFormat(pattern2,Locale.US);

        String datetime = "";

        try {
            Date date = format1.parse(datetext + " " + timetext);
            datetime = format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        table.addTask(taskName,datetime,repeat,list);

        Intent intent = new Intent(AddTask.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
