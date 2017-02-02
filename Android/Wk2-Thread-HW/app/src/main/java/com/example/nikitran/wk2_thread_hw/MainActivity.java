package com.example.nikitran.wk2_thread_hw;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    Button1: text = “Add” will create an array of n items (int) where n = to the number in the
    edit text * 1000. It will generate a random number from 0 to 100 for each item. It will then
    add the items in the array and display the answer in a text view.

    This will be done in a thread.
    */

    public void add(View view)
    {
        // get the int from editText view
        editText = (EditText) findViewById(R.id.editText2);
        String str = editText.getText().toString();
        final int arrSize = Integer.valueOf(str).intValue() * 1000;

        // create a new Thread
        new Thread() {
            @Override
            public void run() {

                int [] intArr = new int[arrSize];
                Random randomNum = new Random();
                int min = 0;
                int max = 100;
                int sum = 0;

                for(int i = 0; i < arrSize; i ++ ){
                    intArr[i] = randomNum.nextInt((max - min) + 1) + min;
                    sum += intArr[i];
                }

                final int finalSum = sum;

                //runs on the UI thread:
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        //replace textView with the sum of Random array
                        textView = (TextView) findViewById(R.id.textView);
                        textView.setText(finalSum);
                    }
                });
            }
        }.start();
    }
    /*
    Button2: text = “Average” will create an array of n items (int) where n = to the number in
    the edit text * 1000. It will generate a random number from 0 to 100 for each item. It will then
    average the items in the array and display the answer in a text view.

    This will be done in an AsyncTask. */

    public void average(View view)
    {
        editText = (EditText) findViewById(R.id.editText2);
        String str = editText.getText().toString();
        int arrSize = Integer.valueOf(str).intValue() * 1000;

        new AsyncTask<Integer, Void, Integer>(){

            @Override
            protected Integer doInBackground(Integer... params) {

                // get the int from editText view:
                int size = params[0];

                int [] intArr = new int[size];
                Random randomNum = new Random();
                int min = 0;
                int max = 100;
                int sum = 0;

                for(int i = 0; i < size; i ++ ){
                    intArr[i] = randomNum.nextInt((max - min) + 1) + min;
                    sum += intArr[i];
                }
                return sum/size; //average
            }

            protected void onPostExecute(int i){
                textView = (TextView) findViewById(R.id.textView);
                //replace textView with the average of Random array
                textView.setText(i);
            }
        }.execute(arrSize);

    }




}
