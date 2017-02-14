package com.example.nikitran.wk4_broadcastrec_dynamically;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver myBR;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Create an intent filter for the reciever
        filter = new IntentFilter();
        filter.addAction("com.example.nikitran.wk4_broadcastrec");

        // 2. Create what to do when the broadcast is reieved
        myBR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Receive by dynamic receiver", Toast.LENGTH_SHORT).show();
            }
        };

        // 3. register the receiver
        registerReceiver(myBR, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(myBR != null)
        {
            // 4. unregister the receiver
            unregisterReceiver(myBR);
            myBR = null;
        }
    }
}
