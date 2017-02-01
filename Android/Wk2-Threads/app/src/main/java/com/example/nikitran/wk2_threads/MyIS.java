package com.example.nikitran.wk2_threads;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

import org.greenrobot.eventbus.EventBus;

public class MyIS extends IntentService {


    public MyIS() {
        super("MyService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int ttS = intent.getIntExtra("K", 1);

        try {

            Thread.sleep(ttS * 1000);
            EventBus.getDefault().post(new ServiceEvent(74, "Done EB2"));
        } catch (InterruptedException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ServiceEvent(74, "EB error"));
        }

    }

}
