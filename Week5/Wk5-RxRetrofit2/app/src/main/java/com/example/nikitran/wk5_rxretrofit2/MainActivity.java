package com.example.nikitran.wk5_rxretrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private List<GitHub> GitHubs2;
    private GitAdapter gitAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitHubs2 = new ArrayList<GitHub>();
    }


    @Override
    protected void onResume() {
        super.onResume();
        callRxRetrofit();

        // Get a reference to the ListView, and attach the adapter to the listView.

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(gitAdapter);
    }

    private void callRxRetrofit() {
        String user = "nikitran";
        Observable<List<GitHub>> resultObservable = RetrofitService.Factory.create(user);

        resultObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<GitHub>>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "onCompleted: Observable is done");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "onError: An error occured.", e);
                }

                @Override
                public void onNext(List<GitHub> GitHubs) {
                    for (GitHub repo : GitHubs) {
                        Log.d(TAG, "onNext: " + repo.toString());
                        GitHubs2.add(repo);

                    }
                    gitAdapter = new GitAdapter(getApplicationContext(), GitHubs);
                    listView.setAdapter(gitAdapter);
                }
            });
    }
}

