package com.fenfei.myapplicationdemo.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;

/**
 * Created by shefenfei on 2017/2/19.
 */

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    private String[] datasets;

    public MyAdapter(String[] datasets) {
        this.datasets = datasets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.content.setText(datasets[position]);
    }

    @Override
    public int getItemCount() {
        return datasets.length;
    }
}

class ViewHolder extends RecyclerView.ViewHolder{

    TextView content;

    public ViewHolder(View itemView) {
        super(itemView);
        content = (TextView) itemView.findViewById(R.id.view_content);
    }
}