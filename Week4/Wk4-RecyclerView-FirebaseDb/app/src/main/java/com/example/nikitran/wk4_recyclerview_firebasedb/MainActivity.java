package com.example.nikitran.wk4_recyclerview_firebasedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button sendButton, clearButton;
    private EditText etName, etPhone;
    private TextView etKey;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String DB_REFERENCE_KEY = "DB2";
    private String key = "";

    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the views:
        sendButton = (Button) findViewById(R.id.send_button);
        clearButton = (Button) findViewById(R.id.clear_button);

        etName = (EditText) findViewById(R.id.editText_name);
        etPhone = (EditText) findViewById(R.id.editText_phone);
        etKey = (TextView) findViewById(R.id.tView_key);

        mRecyclerView = (RecyclerView) findViewById(R.id.rView);
        contacts = new ArrayList<>();

        initialLoad();
        mRecyclerView.setHasFixedSize(true);

        // 2. initialize the LayoutManager(Context) - using LinearLayout here
        //    initialize the adapter with the ArrayList
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mAdapter = new MyAdapter(contacts);

        // 3. connect the Layout manager and adaptor to the RView and
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        // onClick of the send button:
        // 1. creates a new contact and insert it into the database
        // 2. insert it into the
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToDb();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }


    public void insertToDb()
    {
        // 1. create a db reference:
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(DB_REFERENCE_KEY);

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String key = etKey.getText().toString();

        // 2. writing to the db
        Contact c = new Contact();
        c.setName(name);
        c.setPhone(phone);
        c.setKey(key);

        if(key.equals("")) {
            //generate the key
            String newKey = myRef.push().getKey();
            c.setKey(newKey);
            myRef.child(newKey).setValue(c);
        }
        else{   //update existing:
            c.setKey(key);
            myRef.child(key).setValue(c);
        }

        //----------------------------create a listener-----------------------------------
        ValueEventListener myVEL = new ValueEventListener() {

            @Override  // run at the beginning once and when it changes:
            public void onDataChange(DataSnapshot dataSnapshot) {

                contacts.clear();
                for(DataSnapshot DS: dataSnapshot.getChildren())
                {
                    Contact newContact = DS.getValue(Contact.class);
                    contacts.add(newContact);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        // connect the listener to the Db:
        myRef.addValueEventListener(myVEL);

        //clear the text fields:
        clear();
    }

    // When the item is clicked on, allow the user to edit the contract
    // 1. set the edit text field
    // 2. takes the current key
    // 3. calls insert to db and populate with the current key
    public void onItemClicked(View v)
    {
        TextView cName, cPhone, cKey;

        // a. get the ViewGroup - which is the parent:
        ViewGroup vGroup = (ViewGroup) v;

        // b. the children are indexed  are the views within the ViewGroup:
        cName = (TextView) vGroup.getChildAt(0);
        cPhone = (TextView) vGroup.getChildAt(1);
        cKey = (TextView) vGroup.getChildAt(2);

        // c. create a toast with this information:
        Toast.makeText(this, cName.getText().toString() +
                        " with phone: " +
                        cPhone.getText().toString() +
                        " was selected",
                Toast.LENGTH_SHORT)
                .show();

        etName.setText(cName.getText().toString());
        etPhone.setText(cPhone.getText().toString());
        etKey.setText(cKey.getText().toString());

        sendButton.setText("update");
    }

    private void initialLoad()
    {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(DB_REFERENCE_KEY);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override  // run at the beginning once and when it changes:
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot DS: dataSnapshot.getChildren()) {
                    Contact newContact = DS.getValue(Contact.class);
                    contacts.add(newContact);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void clear()
    {
        etName.setText("");
        etPhone.setText("");
        etKey.setText("");

        sendButton.setText("add");
    }
}
