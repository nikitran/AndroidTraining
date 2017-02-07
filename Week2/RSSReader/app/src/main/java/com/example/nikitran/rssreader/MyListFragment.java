package com.example.nikitran.rssreader;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */

// when the button is pressed, updateDetails stores
public class MyListFragment extends Fragment {

    private OnItemSelectedListener listener;

    public MyListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // throws an exception if there are no instance of the OnItemSelectedListener
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rsslist_overview, container, false);

        Button button = (Button) view.findViewById(R.id.updateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail("fake");
            }
        });

        return view;
    }

    // used to send the time to the details fragment
    public interface OnItemSelectedListener {
        // stores this link string
        void onRssItemSelected(String link);
    }

    //triggers update of the details fragment
    public void updateDetail(String fake)
    {
        // create fake data
        String newTime = String.valueOf(System.currentTimeMillis());

        // send data to activity
        // stores the newTime string in the OnItemSelectedzlistener interface class
        listener.onRssItemSelected(newTime);

    }

}
