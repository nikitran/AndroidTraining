package com.example.nikitran.wk4_broadcastrec3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver myBR;
    IntentFilter myFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. add the action
        myFilter = new IntentFilter();
        myFilter.addAction("com.example.nikitran.wk4_broadcastrec");

        // 2. create the broadcast receiver
        myBR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Received: Bring to foreground", Toast.LENGTH_SHORT).show();

                // Bring the main activity to the forefront
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
            }
        };

        // 3. register the receiver and the filter:
        registerReceiver(myBR, myFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(myBR != null)
        {
            // 4. unregister the receiver:
            unregisterReceiver(myBR);
            myBR = null;
        }
    }
}
