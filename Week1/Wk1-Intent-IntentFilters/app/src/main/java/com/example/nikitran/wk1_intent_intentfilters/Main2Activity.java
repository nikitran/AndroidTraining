package com.example.nikitran.wk1_intent_intentfilters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView eT;
    public final static String EXTRA_MESSAGE = "MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        eT = (TextView) findViewById(R.id.textView3);

        String s = getIntent().getStringExtra(EXTRA_MESSAGE);
        eT.setText(s);

    }
}
