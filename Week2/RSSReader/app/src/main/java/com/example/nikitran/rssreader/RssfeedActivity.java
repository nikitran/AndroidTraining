package com.example.nikitran.rssreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RssfeedActivity extends AppCompatActivity implements MyListFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
    }

    @Override
    public void onRssItemSelected(String link) {
        DetailFragment fragment = new DetailFragment();
        fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        fragment.setText(link);
    }
}
