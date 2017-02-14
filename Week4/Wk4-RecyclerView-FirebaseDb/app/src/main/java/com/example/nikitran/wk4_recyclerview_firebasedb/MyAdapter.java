package com.example.nikitran.wk4_recyclerview_firebasedb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nikitran on 2/13/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Contact> Contacts;

    public MyAdapter(List<Contact> contacts) {
        this.Contacts = contacts;
    }

    // 2. Create the ViewHolder, it holds the reference to all the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mName;
        public TextView mPhone;
        public TextView mKey;

        public ViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.textView_name);
            mPhone = (TextView) v.findViewById(R.id.textView_phone);
            mKey = (TextView) v.findViewById(R.id.textView_key);
        }
    }

    // 3. onCreate callback - creates new views (invoked by layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        // b. return the ViewHolder with the item view layout
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(Contacts.get(position).getName());
        holder.mPhone.setText(Contacts.get(position).getPhone());
        holder.mKey.setText(Contacts.get(position).getKey());
    }

    @Override
    public int getItemCount() {
        return Contacts.size();
    }


}
