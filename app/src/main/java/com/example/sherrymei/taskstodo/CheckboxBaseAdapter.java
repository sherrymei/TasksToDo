package com.example.sherrymei.taskstodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.CheckBox;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by SHERRYMEI on 7/15/2018.
 */

public class CheckboxBaseAdapter extends BaseAdapter {

    private ArrayList<Task> taskList;
    private Context context;

    public CheckboxBaseAdapter(Context context, ArrayList<Task> taskList){
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_todo, parent, false);

            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.textView1 = (TextView) convertView.findViewById(R.id.task_title);
            holder.textView2 = (TextView) convertView.findViewById(R.id.datetime);

            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Task taskItem = taskList.get(position);
        holder.textView1.setText(taskItem.getItem());
        holder.textView2.setText(taskItem.getDateTime());
        holder.checkbox.setChecked(taskItem.isDone());

        return convertView;

    }
    private class ViewHolder {
        CheckBox checkbox;
        TextView textView1;
        TextView textView2;

    }
}