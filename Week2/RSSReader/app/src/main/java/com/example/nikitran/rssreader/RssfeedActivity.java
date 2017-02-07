package com.example.nikitran.rssreader;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;


public class RssfeedActivity extends AppCompatActivity implements MyListFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);

        // landscape orientation
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            // all good, we use the fragments defined in the layout
            return;
        }

        // if savedInstanceState is null we do some cleanup
        if (savedInstanceState != null) {

            // cleanup any existing fragments in case we are in detailed mode
            getFragmentManager().executePendingTransactions();
            android.app.Fragment fragmentById = getFragmentManager().findFragmentById(R.id.fragment_container);

            if (fragmentById!=null) {
                getFragmentManager().beginTransaction().remove(fragmentById).commit();
            }
        }

        MyListFragment listFragment = new MyListFragment();
        FrameLayout viewById = (FrameLayout) findViewById(R.id.fragment_container);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).commit();

    }

    @Override
    public void onRssItemSelected(String link) {

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
            fragment.setText(link);

        } else {
            // replace the fragment
            // Create fragment and give it an argument for the selected article

            Fragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putString("url", link);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            transaction.replace(R.id.fragment_container, newFragment);

            // and add the transaction to the back stack so the user can navigate back
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();


            //DetailFragment fragment = new DetailFragment();
            //fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
            //fragment.setText(link);
        }
    }
}
