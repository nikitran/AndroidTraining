package com.example.nikitran.wk3_recyclerview_sqlite;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nikitran on 2/13/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private String[] mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
    //---------------------------------------------------------------------------------------------
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    //---------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

    }

    //---------------------------------------------------------------------------------------------
    @Override
    public int getItemCount() {
        return 0;
    }

}
