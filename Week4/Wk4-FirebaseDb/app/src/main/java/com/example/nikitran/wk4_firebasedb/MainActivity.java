package com.example.nikitran.wk4_firebasedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText etN, etPh;
    Button b1, b2, b3;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etN = (EditText) findViewById(R.id.editText);
        etPh = (EditText) findViewById(R.id.editText2);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);

        tv = (TextView) findViewById(R.id.textView2);

        //----------------------------------------------------------------create a listener for b1
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. create a db reference:
                DatabaseReference myR = FirebaseDatabase.getInstance().getReference("myPlace");

                // 2. writing to the db from edit text value
                myR.setValue(etN.getText().toString());

            }
        });

        // ---------------------------------------------------------------create a listener for b2
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. create a db reference:
                DatabaseReference myR = FirebaseDatabase.getInstance().getReference("contacts");

                // 2. writing to the db
                myR.child("name").setValue(etN.getText().toString());
                myR.child("phone").setValue(etPh.getText().toString());

                //----------------------------create a listener-----------------------------------
                ValueEventListener myVal = new ValueEventListener() {

                    // run at the beginning once and when it changes:
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //get a String from the child = "name" and "phone" of the snapshot
                        String s1 = dataSnapshot.child("name").getValue(String.class);
                        String s2 = dataSnapshot.child("phone").getValue(String.class);

                        // set the textView
                        tv.setText(s1 + " - " + s2);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };

                // connect the listener to the Db:
                myR.addValueEventListener(myVal);
            }
        });

        // ---------------------------------------------------------------create a listener for b3
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. create a db reference:
                DatabaseReference myR = FirebaseDatabase.getInstance().getReference("contacts2");

                // 2. writing to the db
                Contact c = new Contact();
                c.setmName(etN.getText().toString());
                c.setmPhone(etPh.getText().toString());

                myR.setValue(c);

                //----------------------------create a listener-----------------------------------
                ValueEventListener myVal = new ValueEventListener() {

                    @Override  // run at the beginning once and when it changes:
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Contact contact = dataSnapshot.getValue(Contact.class);

                        // set the textView:
                        tv.setText(contact.getmName() + " - " + contact.getmPhone());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };

                // connect the listener to the Db:
                myR.addValueEventListener(myVal);
            }
        });
    }
}
