package com.example.nikitran.wk2_thread_hw;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    EditText eText;
    TextView tView;
    Subscription myS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText = (EditText) findViewById(R.id.editText2);
        tView = (TextView) findViewById(R.id.textView);
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

        // create a new Thread
        new Thread() {
            @Override
            public void run() {

                //long running operation
                int arrSize;
                String str = eText.getText().toString();

                if (!str.trim().isEmpty())
                {
                    arrSize = Integer.valueOf(str).intValue() * 1000;
                }
                else
                {
                    arrSize = 0;
                }

                int [] intArr = new int[arrSize];
                Random randomNum = new Random();

                int sum = 0;

                for(int i = 0; i < arrSize; i ++ ){
                    intArr[i] = randomNum.nextInt(101);
                    sum += intArr[i];
                }
                final String finalSum = String.valueOf(sum);

                //runs on the UI thread:
                tView.post(new Runnable() {
                    @Override
                    public void run() {

                        tView.setText(finalSum);
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
        // get the int from editText view:
        int n;
        String str = eText.getText().toString();
        if (!str.trim().isEmpty())
        {
            n = Integer.valueOf(str).intValue() * 1000;
        }
        else
        {
            n = 0;
        }

        new AsyncTask<Integer, Void, Integer>(){


            @Override
            protected Integer doInBackground(Integer... params) {
                int size = params[0];
                Log.d("main", "" + size);

                if (size == 0) return 0; 

                int [] intArr = new int[size];
                Random randomNum = new Random();
                int sum = 0;

                for(int i = 0; i < size; i ++ ){
                    intArr[i] = randomNum.nextInt((100 - 0) + 1) + 0;
                    sum += intArr[i];
                }
                return sum/size; //average
            }

            protected void onPostExecute(Integer i){
                //replace textView with the average of Random array
                tView.setText("" + i);
            }
        }.execute(n);

    }
    /*Button3: text = “High” will create an array of n items (int) where n = to the number in the
    edit text * 1000. It will generate a random number from 0 to 10000 for each item. It will then
    find the largest item in the array and display the answer in a text view.

    This will be done in an intent service communicating with an EventBus.
    */

    public class MyEvent{
        private int mValue;
        public MyEvent(int val) {
            this.mValue = val;
        }
        public int getVal() {
            return mValue;
        }
    }

    public void high(View view){

        int n;
        String str = eText.getText().toString();

        if (!str.trim().isEmpty())
        {
            n = Integer.valueOf(str).intValue() * 1000;
        }
        else
        {
            n = 0;
        }
        final int arrSize = n;

        new Thread(){
            @Override
            public void run() {

                int [] intArr = new int[arrSize];
                Random randomNum = new Random();
                int highest = 0;

                for(int i = 0; i < arrSize; i ++ ){
                    intArr[i] = randomNum.nextInt((100 - 0) + 1) + 0;
                    if (intArr[i] > highest)
                        highest = intArr[i];
                }
                EventBus.getDefault().post(new MyEvent(highest));
            }
        }.start();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setViewText(MyEvent e) {
        tView.setText("" + e.getVal());
    }

    protected void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    protected void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /*
    Button4: text = “Low” will create an array of n items (int) where n = to the number in the
    edit text * 1000. It will generate a random number from 0 to 10000 for each item. It will then
    find the smallest item in the array and display the answer in a text view.
    This will be done with RxJava.

    */
    public void low(View view){
        int arrSize;
        String str = eText.getText().toString();

        if (!str.trim().isEmpty())
        {
            arrSize = Integer.valueOf(str).intValue() * 1000;
        }
        else
        {
            arrSize = 0;
        }
        doRx(arrSize);
    }

    // observable
    private void doRx(final int arrSize){
        Observable<Integer> myO = Observable.fromCallable(
                new Callable<Integer>(){

                    public Integer call() throws Exception {
                        int [] intArr = new int[arrSize];
                        Random randomNum = new Random();
                        int lowest = 0;

                        for(int i = 0; i < arrSize; i ++ ){
                            intArr[i] = randomNum.nextInt((100 - 0) + 1) + 0;
                            if (intArr[i] < lowest)
                                lowest = intArr[i];
                        }

                        return lowest;
                    }
                }
        );

        //subscription
        myS = myO
                .subscribeOn(Schedulers.newThread())    //run on the a new thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Integer integer) {
                        tView.setText("" + integer);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if((myS != null) && !(myS.isUnsubscribed()))
            myS.unsubscribe();
    }




}
