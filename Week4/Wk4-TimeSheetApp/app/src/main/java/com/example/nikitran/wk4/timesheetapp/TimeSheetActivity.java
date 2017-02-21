package com.example.nikitran.wk4.timesheetapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.nikitran.wk4.timesheetapp.Consts.PREF_KEY_CDM;
import static com.example.nikitran.wk4.timesheetapp.Consts.PREF_KEY_EMAIL;
import static com.example.nikitran.wk4.timesheetapp.Consts.PREF_KEY_NAME;
import static com.example.nikitran.wk4.timesheetapp.Consts.PREF_KEY_PHONE;


public class TimeSheetActivity extends AppCompatActivity {

    private TextView tv_name, tv_phone, tv_email, tv_cdm, tv_totalHours;
    private EditText et_start, et_end, et_today;
    private CheckBox cb_signature;
    SharedPreferences accountInfo;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Day> week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet_actvity);

        accountInfo = getSharedPreferences(Consts.SHARED_PREF_ACCOUNT, MODE_PRIVATE);

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
        // extract from shared pref using a key
        String myName = accountInfo.getString(PREF_KEY_NAME, "");
        tv_name.setText(myName);

        String myPhone = accountInfo.getString(PREF_KEY_PHONE, "");
        String formattedNumber = PhoneNumberUtils.formatNumber(myPhone);
        tv_phone.setText(formattedNumber);

        String myEmail = accountInfo.getString(PREF_KEY_EMAIL, "");
        tv_email.setText(myEmail);

        String myCdm = accountInfo.getString(PREF_KEY_CDM, "");
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
}
