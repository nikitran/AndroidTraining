package com.example.nikitran.wk4.timesheetapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nikitran on 2/14/17.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    public static final int DAYS_OF_WEEK = 7;
    private List<Day> Week;

    // Constructor:
    public DayAdapter(List<Day> thisWeeks) {
        this.Week = thisWeeks;
    }

    // 2. Create the ViewHolder, it holds the reference to all the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mDOW;
        public TextView mDate;
        public EditText mRegular;
        public EditText mPto;
        public EditText mHoliday;

        public ViewHolder(View itemView) {
            super(itemView);
            mDOW = (TextView) itemView.findViewById(R.id.tv_day_of_week);
            mDate = (TextView) itemView.findViewById(R.id.et_date);
            mRegular = (EditText) itemView.findViewById(R.id.et_reg);
            mPto = (EditText) itemView.findViewById(R.id.et_pto);
            mHoliday = (EditText) itemView.findViewById(R.id.et_holiday);
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

        holder.mDOW.setText(Week.get(position).getmDOW().toString());

        if(Week.get(position).getmDate() != null){
            holder.mDate.setText(Week.get(position).getmDate().toString());
        } else
            holder.mDate.setText("");

        if(Week.get(position).getmRegular() > 0.00) {
            holder.mRegular.setText(String.valueOf(Week.get(position).getmRegular()));
        }else
            holder.mRegular.setText("");

        if(Week.get(position).getmPto() > 0.00){
            holder.mPto.setText(String.valueOf(Week.get(position).getmPto()));
        }else
            holder.mPto.setText("");
        if(Week.get(position).getmHoliday() > 0.00) {
            holder.mHoliday.setText(String.valueOf(Week.get(position).getmHoliday()));
        }else
            holder.mHoliday.setText("");
        
    }
    // 5. Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Week.size();
    }
}


