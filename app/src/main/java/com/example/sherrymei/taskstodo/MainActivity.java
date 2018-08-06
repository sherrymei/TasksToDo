package com.example.sherrymei.taskstodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listViewWithCheckbox = (ListView)findViewById(R.id.listView);
        //final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();

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
