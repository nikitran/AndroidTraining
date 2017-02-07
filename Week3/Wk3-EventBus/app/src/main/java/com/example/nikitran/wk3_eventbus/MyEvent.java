package com.example.nikitran.wk3_eventbus;

/**
 * Created by nikitran on 2/7/17.
 */

public class MyEvent {
    private int value;

    public MyEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
