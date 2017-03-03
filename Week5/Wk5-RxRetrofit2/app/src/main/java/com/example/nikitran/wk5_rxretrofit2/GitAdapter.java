package com.example.nikitran.wk5_rxretrofit2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nikitran on 2/23/17.
 */

public class GitAdapter extends BaseAdapter implements Serializable {
    private Context context;
    private List<GitHub> GitHubs;

    public class ViewHolder{
        TextView text;

        public ViewHolder(View text) {
            this.text = (TextView) text;
        }
    }
    public void refresData(List<GitHub> repos) {
        GitHubs = repos;
        notifyDataSetChanged();
    }

    public GitAdapter(Context context, List<GitHub> gitHubs) {
        this.context = context;
        GitHubs = gitHubs;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.layout_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHub repo = (GitHub) getItem(position);
                Intent intent = new Intent (context, DetailActivity.class);
                intent.putExtra(DetailActivity.DETAIL_EXTRA, repo);
                context.startActivity(intent);
            }
        });

        GitHub repo = (GitHub) getItem(position);
        holder.text.setText(repo.getName());

        return convertView;
    }

    @Override
    public int getCount() {
        if (GitHubs != null) {
            return GitHubs.size();
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return GitHubs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
