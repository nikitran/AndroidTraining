package com.example.nikitran.wk2_threads;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    TextView tV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV = (TextView) findViewById(R.id.text_view);
    }

    /////////////////////////////////////////////////// 1. creating a new thread - part of java
    public void onThread(View view) {
        new Thread() {

            //this is a implicit handler
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    tV.post(new Runnable() {     //off of the textView, so it will run in the ui thread

                        @Override
                        public void run() {
                            tV.setText("Done w/ thread");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    ///////////////////////////////////////////////////2. Asynch

    //AsynchTask is a way with working with thread given in Android
    public void onAsynchTask() {

        new AsyncTask<Integer, Void, String>() {    // string = will be used for onPostExecute

            // will run on the background thread, everything else runs on the UI thread
            @Override
            protected String doInBackground(Integer... params) {
                //sleep is placeholder for a long running operation

                int i = params[0];  // get the first element in the array

                try {
                    Thread.sleep(i * 1000);
                    return "Done AsyncTask";    // will to onPostExecute
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return "AT Error";
                }

            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                tV.setText(s);
            }

            ;
        }.execute(1);
    }

    ////////////////////////////////////////////////////3. EventBus


    // simple class that stores a String and int
    public class MyEvent{
        private String msg;
        private int eventCode;

        public MyEvent(int eventCode, String msg) {
            this.msg = msg;
        }

        public int getEventCode() {
            return eventCode;
        }

        public String getMsg() {
            return msg;
        }

    }

    public void onEB (View view){
        new Thread(){
            @Override
            public void run() {
                //super.run();
                android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST); // not all threads have the same priority, set priority
                try {
                    Thread.sleep(1500);
                    EventBus.getDefault().post(new MyEvent(747, "done EB")); // send 2 values
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void whatever(MyEvent e) {
        // use if or switch statment to handle multiple events
        if (e.getEventCode() == 747)
        {
            tV.setText(e.getMsg());
        }
    }

    protected void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    protected void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    ////////////////////////////////////////////////////3. Using a IntentService with eventbus



    public void onEB2 (View view){
        Intent i = new Intent (this, MyIS.class);

        String str = ((EditText)findViewById(R.id.editText)).getText().toString();
        i.putExtra("K", Integer.valueOf(str).intValue());

        startService(i);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void whatever2(ServiceEvent e) {
        // use if or switch statement to handle multiple events
        if (e.getEventCode() == 74)
        {
            tV.setText(e.getMsg());
        }
    }

}

