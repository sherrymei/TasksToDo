package com.example.sherrymei.taskstodo;

/**
 * Created by SHERRYMEI on 7/29/2018.
 */

public class Task {

    private String item;
    private boolean isDone = false;

    public Task(String item, boolean isDone){
        this.item = item;
        this.isDone = isDone;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
