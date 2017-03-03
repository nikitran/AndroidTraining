package com.example.nikitran.wk5_location2;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkGPSIsOn();
        bgaClient();
    }

    private bgaClient(){
        gaClient = new GoogleApiclient.Builder(this)
                .addConnectionCallback(this)
    }

    void checkGPSIsOn()
    {
        LocationManager lm = (LocationManager) getSystemService()
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
