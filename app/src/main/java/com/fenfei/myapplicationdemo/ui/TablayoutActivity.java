package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.fragments.Fragment_1;

/**
 * 测试Tablayout
 */
public class TablayoutActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager_;
    private FrameLayout mFrameLayout_;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        mFragmentManager_ = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        mFrameLayout_ = (FrameLayout) findViewById(R.id.container);
        mFragmentManager_.beginTransaction().add(R.id.container , Fragment_1.newInstance()).commit();
    }


}
