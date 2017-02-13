package com.example.nikitran.wk1_intent_intentfilters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/* intents facilitate communication between components,

The Intent constructor takes two parameters:
- A Context as its first parameter (this is used because the Activity class is a subclass of Context)
- The Class of the app component to which the system should deliver the Intent

The purpose of the app is to explore the common uses of intents, there are 3 main uses:
        1. Start Activity
            - a. explicit
            - b. implicit (action: ACTION_SEND)
        2. Start Service
        3. Delivering Broadcast (not int this app)


Receiving implicit intents - intent filters are used to declare that is app is able to handle
        certain implicit intents (ex. SEND in this app), This is made in the manifest


 */
public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button b1;
    Button b2;
    Button b3;
    public final static String EXTRA_MESSAGE = "MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);

    }

    // 1. Start a explicit activity with a string extra:
    public void onStartExplicitActivity(View v) {

        // 1. First we initialize the intent, with the current context class and the class we want to start
        Intent i = new Intent(this, Main2Activity.class);

        /* optional: intents can store extras in a key-value pair
           here we get String text from the the editText view and usin putExtra to store it into the intent
        */
        String message = editText.getText().toString();
        i.putExtra(EXTRA_MESSAGE, message);

        // 2. start the activity
        startActivity(i);

    }

    // 1. Start a implicit activity with a string extra:
    public void onStartImplicitActivity(View v) {

        // 1. Create the text message and store it in the intent as an extra
        Intent i = new Intent();
        String message = editText.getText().toString();
        i.putExtra(Intent.EXTRA_TEXT, message);

        // 2. set the action and type to be shared
        i.setAction(Intent.ACTION_SEND);
        i.setType("text/plain");

        // 3. Verify that the intent will resolve to an activity
        //
        // call resolveActivity() on your Intent object, to verify that the intent will be received,
        // If the result is non-null, there is at least one app that can handle the intent and it's
        // safe to call startActivity()
        if (i.resolveActivity(getPackageManager()) != null) {
            //startActivity(i);
        }


        //----------Optional: display a chooser dialog for handling the implicit intent-------------

        // Message that will be displayed
        String title = "Share this message with";

        // Create another intent to show the chooser dialog
        Intent chooser = Intent.createChooser(i, title);

        // Verify the original intent will resolve to at least one activity
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }

    }

    public void onStartService(View v){

        // 1. Initialize the service
        Intent i = new Intent(this, MyIntentService.class);

        // 2. call the startService() to start the service in the background
        startService(i);
    }


}
