package com.example.nikitran.wk5_retrofit_rxjava_glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static final String TAG = "MainActivity";

    private Example Randoms;
    private List<Result> ResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rView);
        ResultList = new ArrayList<Result>();

        // 2. initialize the LayoutManager(Context) - using LinearLayout here
        //    initialize the adapter with the ArrayList
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        // 3. connect the Layout manager and adaptor to the RView and
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        callRxRetrofit();
    }

    private void callRxRetrofit() {
        int amount = 20;
        Observable<Example> resultObservable = RetrofitService.Factory.create(amount);

        resultObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Example>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Observable is done");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: An error occured.", e);
                    }

                    @Override
                    public void onNext(Example randomUsers) {
                        Randoms = randomUsers;
                        ResultList = Randoms.getResults();

                        for(Result result: ResultList)
                        {
                            Log.d(TAG, result.toString());
                        }


                        mAdapter = new ProfileAdapter(getApplicationContext(), ResultList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
    }
}
