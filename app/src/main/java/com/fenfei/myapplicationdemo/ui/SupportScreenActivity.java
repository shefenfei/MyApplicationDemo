package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.fragments.NewsDetailFragment;

//应用的UI适配测试
public class SupportScreenActivity extends AppCompatActivity {

    private boolean IS_TWO_PANEL = false; //是否显示两个面板

    private FragmentManager mFragmentManager;
    private NewsDetailFragment mDetailFragment_;

    private String TAG = "SupportScreenActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_panel);

        mFragmentManager = getSupportFragmentManager();
        IS_TWO_PANEL = mFragmentManager.findFragmentByTag("detail_fragemnt") != null;
        if (IS_TWO_PANEL) {
            mDetailFragment_ = (NewsDetailFragment) mFragmentManager.findFragmentByTag("detail_fragemnt");
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }
}