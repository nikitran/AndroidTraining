package com.example.nikitran.wk2_debugging;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by nikitran on 2/6/17.
 */

public class MyService extends IntentService {

    public MyService() {
        super("MyService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int result = 0;
        int val1 = intent.getIntExtra("key_val1", -1);
        int val2 = intent.getIntExtra("key_val2", -1);
        String operation = intent.getStringExtra("key_op");

        Math mathClass = new Math(val1, val2);

        if (operation !=null && operation.equals("add"))
        {
            result = mathClass.add();
        }
        if (operation !=null && operation.equals("multiply"))
        {
            result = mathClass.multiply();
        }

        EventBus.getDefault().post(new MyEvent(result));

    }
}
