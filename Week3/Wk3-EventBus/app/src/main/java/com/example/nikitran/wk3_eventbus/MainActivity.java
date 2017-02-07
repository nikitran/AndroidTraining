package com.example.nikitran.wk3_eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    final String KEY_INPUT = "key_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);

    }

    public void onClick(View view)
    {
        String inputStr = editText.getText().toString();

        Intent i = new Intent(this, MyIntentService.class);
        i.putExtra(KEY_INPUT, Integer.valueOf(inputStr).intValue());

        startService(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void postResult(MyEvent e)
    {
        textView.setText("" + e.getValue());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().register(this);
    }
}
