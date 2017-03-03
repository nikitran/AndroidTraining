package com.example.nikitran.wk5_googlelocation;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

// Asynchronous programming steps
// 1. Create GoogleApiClient
// 2. extent with Connection Callbacks
// 3. extend with OnconnectionFailedListener
// 4. after connecting, wait wait for onConnected callback
// 5. follow the requirement for specific api

// fused location provider - finds your location
// activity recognition api - determine if your driving, walking, running

/* Google Api Client - Asynchronous Communication
  -- Location Services
  --- implement GoogleApiCLient.ConnectionCallbacks
  --- implement GoogleApiClient.OnconnectionFailedListener
  --- LocationListening
 */

public class MainActivity extends AppCompatActivity // 2. extend
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String TAG = "MA";
    private TextView txtOutput;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOutput = (TextView) findViewById(R.id.textOutput);

        // 1. setting up a new GoogleApiClient using builder
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)               //calls back to this class when connects
                .addOnConnectionFailedListener(this)        //calls back to this class when failed
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();          // 2. connect the client
    }


    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();       // 3. disconnect the client
    }

    // fires when connected:
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000); //every sec
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "GoogleApiClient connection has been suspended");
    }

    // fires when connection fails
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleApiClient connection has failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, location.toString());
        txtOutput.setText(Double.toString(location.getLatitude()));
    }
}
