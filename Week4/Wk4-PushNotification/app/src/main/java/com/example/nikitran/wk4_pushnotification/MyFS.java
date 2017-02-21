package com.example.nikitran.wk4_pushnotification;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by nikitran on 2/15/17.
 */

public class MyFS extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("MyFS", "msg: " + remoteMessage.getData().get("Key"));
    }
}
