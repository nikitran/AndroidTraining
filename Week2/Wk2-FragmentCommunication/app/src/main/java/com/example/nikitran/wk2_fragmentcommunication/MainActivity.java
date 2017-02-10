package com.example.nikitran.wk2_fragmentcommunication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements F1.F1Interface, F2.F2Interface{

    F1 f1;
    F2 f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.
        FragmentManager fm = getSupportFragmentManager();

        // initializing F1 and F2
        f1 = (F1) fm.findFragmentById(R.id.forF1);
        f2 = (F2) fm.findFragmentById(R.id.forF2);

        if (f1 == null) {
            f1 = new F1();
        }
        if (f2 == null){
            f2 = new F2();
        }

        //2.
        FragmentTransaction ft = fm.beginTransaction();

        //add frame layout of forF1 with f1
        ft.add(R.id.forF1, f1);
        ft.add(R.id.forF2, f2);

        //
        ft.commit();

    }

    // without interface
    public void sendto2(String s){
        f2.setMyEditText(s);
    }

    //
    public void sendto1(String s){
        f1.setMyEditText(s);
    }

    // using the interface
    @Override
    public void iSendTo2(String s) {
        f2.setMyEditText(s);
    }
}
