package com.example.nikitran.wk4_broadcastrec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by nikitran on 2/14/17.
 */

public class MyBR1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Disconnected from power", Toast.LENGTH_SHORT).show();
    }
}
