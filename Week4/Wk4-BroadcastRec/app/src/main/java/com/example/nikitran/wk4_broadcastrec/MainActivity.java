package com.example.nikitran.wk4_broadcastrec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Broadcasts are sent with an intent
    // need to specify a action so that other apps are able to recieve
    public void onSendBC(View v) {

        Intent broadcastIntent = new Intent();

        // use the namespace for best practice:
        broadcastIntent.setAction("com.example.nikitran.wk4_broadcastrec");
        broadcastIntent.putExtra("key", "Luis");
        sendBroadcast(broadcastIntent);
    }
}
