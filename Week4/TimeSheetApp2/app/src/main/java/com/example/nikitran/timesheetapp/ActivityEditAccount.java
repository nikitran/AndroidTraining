package com.example.nikitran.timesheetapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_CDM;
import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_EMAIL;
import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_NAME;
import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_PHONE;

public class ActivityEditAccount extends AppCompatActivity {

    EditText et_name, et_phone, et_email, et_cdm;
    SharedPreferences accountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        Log.d("ActivityEditAccount:", "Activity started");

        accountInfo = getSharedPreferences(Consts.SHARED_PREF_ACCOUNT, MODE_PRIVATE);

        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_number);
        et_email = (EditText) findViewById(R.id.et_email);
        et_cdm = (EditText) findViewById(R.id.et_cdm);

        fillIn();
    }

    public void onSave(View v) {
        // a. get the editor to write into
        SharedPreferences.Editor editor = accountInfo.edit();

        // b. put an exta in the shared preference object using a key:
        editor.putString(PREF_KEY_NAME, et_name .getText().toString());
        editor.putString(PREF_KEY_PHONE,et_phone.getText().toString());
        editor.putString(PREF_KEY_EMAIL,et_email.getText().toString());
        editor.putString(PREF_KEY_CDM, et_cdm.getText().toString());

        // c. commit() or use apply() - update on the main thread
        editor.commit();

        Intent i = new Intent(this, ActivityFirst.class);
        startActivity(i);
    }

    public void fillIn() {
        // extract from shared pref using a key
        String myName = accountInfo.getString(PREF_KEY_NAME, "");
        et_name.setText(myName);

        String myPhone = accountInfo.getString(PREF_KEY_PHONE, "");
        et_phone.setText(myPhone);

        String myEmail = accountInfo.getString(PREF_KEY_EMAIL, "");
        et_email.setText(myEmail);

        String myCdm = accountInfo.getString(PREF_KEY_CDM, "");
        et_cdm.setText(myCdm);

    }
}
