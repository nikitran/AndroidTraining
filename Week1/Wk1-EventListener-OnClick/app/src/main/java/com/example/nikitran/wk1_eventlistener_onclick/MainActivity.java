package com.example.nikitran.wk1_eventlistener_onclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/* The purpose of this app is to explore the 2 different methods of making a view
    clickable and look at the different event listeners. When the buttons are clicked
    it cahans

    There are 2 ways of making a view clickable:

    1. declare the method that is to be called in the XML
        - android:onClick="onButtonClick"

    2. onClickListener Interface and implement onClick()

 */


public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the textView and button2
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button2);


        /* Method 2:
                  1. Connect a event listener class to the button using setOnclickListener()
                      that takes in a OnclickListener object
                  2. declare the OnClickListner interface inline
                  3. override the onClick() method - this method runs when the view is
                      clicked on
        */
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView.setText("OnClickListener");
            }
        });

        /* Same as above: but by creating an anonymous implementation of OnClickListener

            private OnClickListener myListener = new OnClickListener() {
            public void onClick(View v) {
                textView.setText("OnClickListener");
            }

            // Register the onClick listener with the implementation above
            button.setOnClickListener(myListener);
        };

         */
    }

    // Method 1: this method is called when button1 is clicked on, declared in the xml
    public void onButtonClick(View view)
    {
        textView.setText("XML");
    }
}
