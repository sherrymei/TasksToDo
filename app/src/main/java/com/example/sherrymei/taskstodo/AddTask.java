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
                        Calendar calendar = new GregorianCalendar(selectedYear, selectedMonth, selectedDay);
                        String dayString = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
                        String monthString = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
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
                        String timeString = String.format(Locale.US, "%02d:%02d", selectedHour, selectedMinute) + " " + am_pm;
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
        //// TODO: make tasks repeatable
          // have repeat an alert box to repeat task
        //// TODO: make user create own lists and also add to their own list

        String taskName = taskTxt.getText().toString();
        String dateText = dPicker.getText().toString();
        String timeText = tPicker.getText().toString();
        String repeat = spinner.getSelectedItem().toString();
        String list = "Default";

        String delims = "[ ,]+";
        String dateTimeText = dateText + " " + timeText;
        String[] tokens = dateTimeText.split(delims);
        String month = formatMonth(tokens[1]);
        if (!timeText.equals(""))
            table.addTask(taskName,tokens[0],month,tokens[2],tokens[3],tokens[4],tokens[5],repeat,list);
        else
            table.addTask(taskName,tokens[0],month,tokens[2],tokens[3],"","",repeat,list);

        Intent intent = new Intent(AddTask.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private String formatMonth(String mmm){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat in = new SimpleDateFormat("MMM", Locale.US);
        SimpleDateFormat out = new SimpleDateFormat("MM", Locale.US);
        String month = mmm;
        try{
            calendar.setTime(in.parse(mmm));
            month = out.format(calendar.getTime());
        }
        catch (ParseException e){}
        return month;
    }
}
