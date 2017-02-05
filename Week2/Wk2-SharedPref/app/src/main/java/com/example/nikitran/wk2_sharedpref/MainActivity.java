package com.example.nikitran.wk2_sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText eT;

    @Override
    protected void onStop() {
        super.onStop();

        // 1. writing to the shared preference file
        SharedPreferences settings = getSharedPreferences("File", MODE_PRIVATE);
        // get the editor to write into
        SharedPreferences.Editor editor = settings.edit();

        // put an exta in the shared preference object using a key:
        editor.putString("myKey", eT.getText().toString());
        // apply() - update on the main thread or commit()
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the et id for editText
        eT = (EditText) findViewById(R.id.editText);

        // 2. reading from shared preference
        SharedPreferences settings  = getSharedPreferences("File", MODE_PRIVATE);
        // extract from shared pref using a key, then set it
        String myInfo = settings.getString("myK", "Empty");  //"Empty" = default value
        eT.setText(myInfo);
    }

}
