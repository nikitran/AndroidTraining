package com.example.nikitran.wk5_retrofit_rxjava_glide;

import android.app.Application;
import android.content.Context;

/**
 * Created by nikitran on 2/24/17.
 */

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}