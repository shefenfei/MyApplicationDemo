package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.views.PullToRefreshLayout;

public class ScrollDemoActivity extends AppCompatActivity {

    private String TAG = "ScrollDemoActivity";

    private PullToRefreshLayout mRefreshLayout_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_demo);

        mRefreshLayout_ = (PullToRefreshLayout) findViewById(R.id.scroll_layout);
        mRefreshLayout_.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void refresh() {
                Log.e(TAG, "refresh: ");
            }

            @Override
            public void onLoadMore() {

            }
        });
        String[] datas = {"A" ,"B" , "C" ,"D","A" ,"B" , "C" ,"D","A" ,"B" , "C" ,"D","A" ,"B" , "C" ,"D" };
        mRefreshLayout_.setAdapter(new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , datas));
    }
}
