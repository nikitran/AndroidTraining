package com.example.nikitran.wk4_timesheetapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nikitran on 2/14/17.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    private List<Contact> mContacts;

    // Constructor:
    public MyAdapter(List<Contact> mContacts) {
        this.mContacts = mContacts;
    }

    // 2. Create the ViewHolder, it holds the reference to all the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cName;
        public TextView cPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            cName = (TextView) itemView.findViewById(R.id.tvName);
            cPhone = (TextView) itemView.findViewById(R.id.tvPhone);
        }
    }

    // 3. onCreate callback - creates new views (invoked by layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // a. Create a View using the item_layout
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_day, parent, false);

        // b. return the ViewHolder with the item view layout
        return new ViewHolder(v);
    }

    // 4. onBind  call back - Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //holder.cName.setText(mContacts.get(position).getName());
        //holder.cPhone.setText(mContacts.get(position).getPhone());
    }

    // 5. Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mContacts.size();
    }


}


