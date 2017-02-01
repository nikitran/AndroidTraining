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
        SharedPreferences settings = getSharedPreferences("File", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("myKey", eT.getText().toString());
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eT = (EditText) findViewById(R.id.editText);

        SharedPreferences settings  = getSharedPreferences("File", MODE_PRIVATE);
        String myInfo = settings.getString("myK", "Empty"); //"Empty" = default value
        eT.setText(myInfo);
    }

}
