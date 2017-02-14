package com.example.nikitran.wk4_recyclerview_firebasedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText etN, etPh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        etN = (EditText) findViewById(R.id.editText3);
        etPh = (EditText) findViewById(R.id.editText4);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpDb();
            }
        });
    }

    public void setUpDb()
    {
        // 1. create a db reference:

        DatabaseReference myR = FirebaseDatabase.getInstance().getReference("contacts");

        // 2. writing to the db

        //Contact c = new Contact();
        //c.setmName(etN.getText().toString());
        //c.setmPhone(etPh.getText().toString());

        //myR.setValue(c);

        myR.child("mName").setValue(etN.getText().toString());
        myR.child("mPhone").setValue(etPh.getText().toString());



        //----------------------------create a listener-----------------------------------
        ValueEventListener myVal = new ValueEventListener() {

            @Override  // run at the beginning once and when it changes:
            public void onDataChange(DataSnapshot dataSnapshot) {
                Contact contact = dataSnapshot.getValue(Contact.class);

                // set the textView:
                //tv.setText(contact.getmName() + " - " + contact.getmPhone());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        // connect the listener to the Db:
        myR.addValueEventListener(myVal);
    }
}
