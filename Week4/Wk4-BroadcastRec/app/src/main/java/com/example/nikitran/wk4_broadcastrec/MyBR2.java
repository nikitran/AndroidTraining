package com.example.nikitran.wk4_broadcastrec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBR2 extends BroadcastReceiver {
    public MyBR2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "com.example.nikitran.wk4_broadcastrec BC recieved", Toast.LENGTH_SHORT).show();
    }
}
