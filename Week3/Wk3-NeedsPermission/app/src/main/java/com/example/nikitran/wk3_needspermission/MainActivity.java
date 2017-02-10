package com.example.nikitran.wk3_needspermission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void noGoToOtherApp()
    {
        Intent i = new Intent();
        i.setAction("com.nikitran.myPermission");
        i.addCategory("android.intent.category.DEFAULT");

        startActivity(i);
    }
}
