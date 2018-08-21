package com.example.sherrymei.taskstodo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private tasksTable table;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase;

        try {
            sqLiteDatabase = openOrCreateDatabase(DatabaseHelper.DATABASE_NAME, MODE_PRIVATE, null);
            databaseHelper = new DatabaseHelper(this);
            table = new tasksTable(this,sqLiteDatabase,databaseHelper);
        }
        catch(SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
        ListView listViewWithCheckbox = (ListView)findViewById(R.id.listView);
        ArrayList<Task> tasks = table.getAll();
        CheckboxBaseAdapter checkboxAdapter = new CheckboxBaseAdapter(this,tasks);
        listViewWithCheckbox.setAdapter(checkboxAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_task:
                Log.d(TAG, "Add a new task");
                Intent addIntent = new Intent (MainActivity.this,AddTask.class);
                startActivity(addIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
