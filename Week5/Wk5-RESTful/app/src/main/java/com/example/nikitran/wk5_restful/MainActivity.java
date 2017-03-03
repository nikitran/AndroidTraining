package com.example.nikitran.wk5_restful;

import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.os.StrictMode.setThreadPolicy;

public class MainActivity extends AppCompatActivity {

    //define the network call
    private static final String BASE_URL = "https://randomuser.me/api";
    private static final String TAG = "MainActivity";

    // 1. native connection:
    private URL url;
    private HttpURLConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BAD PRACTICE:
        ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);

    }

    @Override
    protected void onResume() {
        super.onResume();
        performNetworkCall();

        // BEST PRACTICE:
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                performNetworkCall();
//            }
//        }).start();
    }

    private void performNetworkCall()
    {
        try {
            url = new URL(BASE_URL);
            connection = (HttpURLConnection) url.openConnection();

            InputStream input = connection.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;

            while((line = reader.readLine()) != null){
                builder.append(line);
            }

            String result = builder.toString();
            Log.d(TAG, "performNetworkCall: " + result);

        } catch (Exception e) {
            e.printStackTrace();

        } finally{
            if(connection != null){
                connection.disconnect();
            }
        }


    }
}
