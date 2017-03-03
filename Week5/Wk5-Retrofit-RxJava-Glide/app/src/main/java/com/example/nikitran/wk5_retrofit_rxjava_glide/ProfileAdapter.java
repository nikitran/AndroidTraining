package com.example.nikitran.wk5_retrofit_rxjava_glide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by nikitran on 2/23/17.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private List<Result> resultList;
    private Context context;

    public ProfileAdapter(Context context, List<Result> rList) {
        this.context = context;
        this.resultList = rList;
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View termView = inflater.inflate(R.layout.profile_layout, parent, false);

        return new ViewHolder(termView);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {

        String name = resultList.get(position).getName().getFirst() + " " + resultList.get(position).getName().getLast();
        String number = resultList.get(position).getPhone();

        holder.Name.setText(name);
        holder.Number.setText(number);
        final int myPosition = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Recycle Click" + myPosition, Toast.LENGTH_SHORT).show();

                Result result = (Result) resultList.get(myPosition);
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra(DetailActivity.DETAIL_EXTRA, result);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView Name;
        public final TextView Number;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.name);
            Number = (TextView) itemView.findViewById(R.id.number);
        }
    }
}