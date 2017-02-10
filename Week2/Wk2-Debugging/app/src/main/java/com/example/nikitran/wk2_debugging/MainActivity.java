package com.example.nikitran.wk2_debugging;

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

    EditText val1;
    EditText val2;
    String operation;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val1 = (EditText) findViewById(R.id.editText1);
        val2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView2);
    }

    public void onButton1(View view)
    {
        operation = "add";
        invokeService(operation);
    }

    public void onButton2(View view)
    {
        operation = "multiply";
        invokeService(operation);
    }

    private void invokeService(String operation) {
        Intent i = new Intent (this, MyService.class);

        String str = ((EditText)findViewById(R.id.editText1)).getText().toString();

        if (!str.trim().isEmpty())
            i.putExtra("key_val1", Integer.valueOf(str).intValue());

        String str2 = ((EditText)findViewById(R.id.editText2)).getText().toString();
        if (!str2.trim().isEmpty())
            i.putExtra("key_val2", Integer.valueOf(str2).intValue());

        if (!this.operation.trim().isEmpty())
            i.putExtra("key_op", this.operation);

        startService(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showResult(MyEvent e) {
        // use if or switch statement to handle multiple events

            textView.setText(e.getVal() + "");

    }

    protected void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    protected void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
