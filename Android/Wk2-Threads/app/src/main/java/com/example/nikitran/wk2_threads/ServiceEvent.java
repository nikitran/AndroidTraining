package com.example.nikitran.wk2_threads;

/**
 * Created by nikitran on 2/1/17.
 */

public class ServiceEvent {
    private String msg;
    private int eventCode;

    public ServiceEvent(int eventCode, String msg) {
        this.eventCode = eventCode;
        this.msg = msg;
    }

    public int getEventCode() {
        return eventCode;
    }

    public String getMsg() {
        return msg;
    }
}
