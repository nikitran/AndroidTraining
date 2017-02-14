package com.example.nikitran.wk4_broadcastrec2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("key");
        Toast.makeText(context, "Received BC from another app " + "hello " + str, Toast.LENGTH_SHORT).show();
    }
}
