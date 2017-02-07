package com.example.nikitran.wk3_eventbus;

import android.app.IntentService;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by nikitran on 2/7/17.
 */

public class MyIntentService extends IntentService {

    final String KEY_INPUT = "key_1";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int input = intent.getIntExtra(KEY_INPUT, -1);

        if (input >= 0 && input <= 10)
        {
            int result = factorial(input);

            MyEvent e = new MyEvent(result);
            EventBus.getDefault().post(e);
        }
    }

    public int factorial(int n)
    {
        if (n == 0 || n == 1) return 1;
        return factorial(n-1) * n;
    }
}
