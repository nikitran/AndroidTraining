package com.example.nikitran.wk5_rxretrofit2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_EXTRA = "com.example.nikitran.wk5_rxretrofit2.DETAIL_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView name = (TextView) findViewById(R.id.textView2);
        TextView desc = (TextView) findViewById(R.id.textView3);

        Intent intent = getIntent();
        GitHub repo = (GitHub) intent.getSerializableExtra(DETAIL_EXTRA);
    }
}
