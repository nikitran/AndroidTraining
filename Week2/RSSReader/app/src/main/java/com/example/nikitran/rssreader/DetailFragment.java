package com.example.nikitran.rssreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //extract the url from the bundle with the key: "url"
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            String link = bundle.getString("url");
            setText(link);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the fragment_rssitem_detail layout:
        View view = inflater.inflate(R.layout.fragment_rssitem_detail, container, false);
        return view;
    }

    //set the url to the textview in the rssitem_detail layout
    public void setText(String url)
    {
        TextView textView = (TextView) getView().findViewById(R.id.detailsText);
        textView.setText(url);
    }
}
