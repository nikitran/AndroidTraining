package com.example.nikitran.wk4_pushnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();

        if(i != null){
            Toast.makeText(this, "His name is: " + i.getStringExtra("Key"), Toast.LENGTH_SHORT).show();
        }
    }
}
