package com.example.nikitran.timesheetapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

import static com.example.nikitran.timesheetapp.Consts.DB_REFERENCE_KEY;
import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_CDM;
import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_EMAIL;
import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_NAME;
import static com.example.nikitran.timesheetapp.Consts.PREF_KEY_PHONE;


public class ActivityTimeSheet extends AppCompatActivity {

    private TextView tv_name, tv_phone, tv_email, tv_cdm, tv_totalHours;
    private EditText et_start, et_end, et_today;
    private CheckBox cb_signature;
    SharedPreferences accountInfo;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Employee employee;
    private List<Day> week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet);

        accountInfo = getSharedPreferences(Consts.SHARED_PREF_ACCOUNT, MODE_PRIVATE);
        employee = createEmployee();

        // initialize all the views in the
        tv_name = (TextView) findViewById(R.id.textView_name);
        tv_phone = (TextView) findViewById(R.id.textView_phone);
        tv_email = (TextView) findViewById(R.id.textView_email);
        tv_cdm = (TextView) findViewById(R.id.textView_cdm);

        et_start = (EditText) findViewById(R.id.editText_startDate);
        et_end = (EditText) findViewById(R.id.editText_endDate);

        tv_totalHours = (TextView) findViewById(R.id.textView_total_hours);

        cb_signature = (CheckBox) findViewById(R.id.checkBox_signature);
        et_today = (EditText) findViewById(R.id.editText_today);

        mRecyclerView = (RecyclerView) findViewById(R.id.rView);
        week = new ArrayList<>();

        populateWeek();
        mRecyclerView.setHasFixedSize(true);

        // 2. initialize the LayoutManager(Context) - using LinearLayout here
        //    initialize the adapter with the ArrayList
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mAdapter = new DayAdapter(week);

        // 3. connect the Layout manager and adaptor to the RView and
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        displayAccountInfo();

    }

    public void displayAccountInfo()
    {
        String myName = employee.getmName();
        tv_name.setText(myName);

        String formattedNumber = PhoneNumberUtils.formatNumber(employee.getmPhone());
        tv_phone.setText(formattedNumber);

        String myEmail = employee.getmEmail();
        tv_email.setText(myEmail);

        String myCdm = employee.getmCDM();
        tv_cdm.setText(myCdm);
    }

    public void populateWeek()
    {
        for(int i = 0; i < 7; i++) {
            Day day = new Day();
            week.add(day);
        }
        week.get(0).setmDOW("Sun");
        week.get(1).setmDOW("Mon");
        week.get(2).setmDOW("Tue");
        week.get(3).setmDOW("Wed");
        week.get(4).setmDOW("Thu");
        week.get(5).setmDOW("Fri");
        week.get(6).setmDOW("Sat");
    }

    public void createTimeData()
    {
        ViewGroup RView = (ViewGroup) findViewById(R.id.rView);


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
    public Employee createEmployee(){

        Employee newEmp = new Employee();
        // extract from shared pref using a key
        String myName = accountInfo.getString(PREF_KEY_NAME, "");
        newEmp.setmName(myName);

        String myPhone = accountInfo.getString(PREF_KEY_PHONE, "");
        newEmp.setmPhone(myPhone);

        String myEmail = accountInfo.getString(PREF_KEY_EMAIL, "");
        newEmp.setmEmail(myEmail);

        String myCdm = accountInfo.getString(PREF_KEY_CDM, "");
        newEmp.setmCDM(myCdm);
    }

    public void insertToDb()
    {
        TimeSheet newTS = new TimeSheet();
        newTS.setmEmployee(employee);


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
}
