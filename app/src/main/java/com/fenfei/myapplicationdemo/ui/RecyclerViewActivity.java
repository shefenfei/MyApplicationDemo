package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.views.MyAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView_;
    RecyclerView.Adapter mAdapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView_ = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView_.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView_.setHasFixedSize(true);

        mAdapter_ = new MyAdapter(buildData());
        mRecyclerView_.setAdapter(mAdapter_);
    }

    private String[] buildData() {
        String[] datasets = {"数据1" , "数据1" ,"数据1" ,"数据1" ,"数据1" ,"数据1"};
        return datasets;
    }
}
