package com.example.nikitran.timesheetapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_NAME;

public class ActivityFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences accountInfo  = getSharedPreferences(Consts.SHARED_PREF_ACCOUNT, MODE_PRIVATE);

        // extract from shared pref using a key
        String myName = accountInfo.getString(PREF_KEY_NAME, "Stranger").toUpperCase();
        String arrName[] = myName.split(" ");

        setTitle("HELLO "+ arrName[0] + "!");

        final Intent accountIntent = new Intent(this, ActivityEditAccount.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(accountIntent);
            }
        });
    }

    public void onTimeSheet(View v)
    {
        Intent TimeSheetIntent = new Intent(this, ActivityTimeSheet.class);
        startActivity(TimeSheetIntent);
    }
}
