package com.example.nikitran.wk6_fingerpringauth;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

/**
 * Created by nikitran on 2/28/17.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    private Context context;

    public FingerprintHandler(Context context){
        this.context = context;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject){
        cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT != PackageManager.PERMISSION_GRANTED)){
            return; //no permissions
        }

        manager.authenticate(cyr);
    }
}
