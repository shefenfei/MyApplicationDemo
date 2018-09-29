package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.fragments.AFragment;
import com.fenfei.myapplicationdemo.fragments.BFragment;

public class FragmentTestActivity
        extends AppCompatActivity
            implements BFragment.OnFragmentInteractionListener{

    FragmentManager mFragmentManager_;

    public RequestManager mRequestManager_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_fragment_test);
        mFragmentManager_ = getSupportFragmentManager();
        mRequestManager_ = Glide.with(this);
    }

    @Override
    public void onFragmentInteraction(String text) {
        AFragment aFragment = (AFragment) mFragmentManager_.findFragmentById(R.id.fragment_a);
        aFragment.changeText(text);
    }
}
