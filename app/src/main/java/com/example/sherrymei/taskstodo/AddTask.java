package com.example.sherrymei.taskstodo;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddTask extends AppCompatActivity {

     public EditText mdatePicker;
     public EditText mtimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
         mdatePicker = (EditText) findViewById(R.id.editText2);
         mtimePicker = (EditText) findViewById(R.id.editText3);


        mdatePicker.setOnClickListener(new View.OnClickListener() {

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

                        String dateString = (dayString + " " + monthString + " " + selectedDay + ", " + selectedYear);
                        mdatePicker.setText(dateString);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

        mtimePicker.setOnClickListener(new View.OnClickListener() {
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
                        String timeString = String.format("%02d:%02d", selectedHour, selectedMinute) + am_pm;
                        mtimePicker.setText(timeString);
                    }
                },hour,minute, false);
                mTimePicker.show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tasks_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void addTask(View view) {

    }

}
