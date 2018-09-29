package com.fenfei.myapplicationdemo.studemo.day09;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.fenfei.myapplicationdemo.R;

public class Day09Activity extends AppCompatActivity implements LifecycleOwner{

    private LifecycleRegistry mLifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day09);

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.addObserver(new MyObserver(this, mLifecycleRegistry));

//        Transformations.map()
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}




