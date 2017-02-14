package com.example.nikitran.wk3_recyclerview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Contact> cL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call to populate the ArrayList:
        initContacts();

        // 1. initialize the RecyclerView:
        mRecyclerView = (RecyclerView) findViewById(R.id.rView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // 2. initialize the LayoutManager(Context) - using LinearLayout here
        //    initialize the adapter with the ArrayList
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mAdapter = new MyAdapter(cL);

        // 3. connect the Layout manager and adaptor to the RView and
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

    private void initContacts(){
        cL = new ArrayList<>();
        cL.add(new Contact("Joe", "123"));
        cL.add(new Contact("Claudia", "21"));
        cL.add(new Contact("Bob", "747"));

        cL.add(new Contact("Laila2", "22"));
        cL.add(new Contact("Joe1", "123"));
        cL.add(new Contact("Claudia1", "21"));

        cL.add(new Contact("Bob2", "747"));
        cL.add(new Contact("Laila2", "22"));
        cL.add(new Contact("Joe2", "123"));

        cL.add(new Contact("Claudia3", "21"));
        cL.add(new Contact("Bob3", "747"));
        cL.add(new Contact("Laila3", "22"));
    }

    // This called when any of the Views are clicked on
    public void onItemClicked(View view) {
        TextView cN, cPh;

        // a. get the ViewGroup - which is the parent:
        ViewGroup vG = (ViewGroup) view;

        // b. the children are indexed  are the views within the ViewGroup:
        cN = (TextView) vG.getChildAt(0);
        cPh = (TextView) vG.getChildAt(1);

        // c. create a toast with this information:
        Toast.makeText(this, cN.getText().toString() +
                        " with phone: " +
                        cPh.getText().toString() +
                        " was selected",
                Toast.LENGTH_SHORT)
                .show();

    }
}
