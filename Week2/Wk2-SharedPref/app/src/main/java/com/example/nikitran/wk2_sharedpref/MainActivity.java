package com.example.nikitran.wk2_sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText eT;
    private static final String PREF_KEY = "key";
    private static final String PREF_NAME = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the et id for editText
        eT = (EditText) findViewById(R.id.editText);

        // 2. reading from shared preference -----------------------------------------------------

        // get SharedPreferences with the getSharedPreferences(String name, mode int) from Context
        SharedPreferences settings  = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // extract from shared pref using a key
        String myInfo = settings.getString(PREF_KEY, "Empty");  //"Empty" = default value
        eT.setText(myInfo);
    }

    /*
        we are saving the preference data in the onStop callback, onPause would be a better options
        because applications killed by the system never gets to onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();

        // 1. writing to the shared preference file-----------------------------------------------

        // get SharedPreferences with the getSharedPreferences(String name, mode int) from Context
        SharedPreferences settings = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // a. get the editor to write into
        SharedPreferences.Editor editor = settings.edit();

        // b. put an exta in the shared preference object using a key:
        editor.putString(PREF_KEY, eT.getText().toString());

        // c. commit() or use apply() - update on the main thread
        editor.commit();
    }

}
