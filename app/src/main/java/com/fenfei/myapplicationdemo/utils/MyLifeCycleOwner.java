package com.fenfei.myapplicationdemo.utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

/**
 * Created by shefenfei on 2017/11/8.
 */

public class MyLifeCycleOwner implements LifecycleOwner {

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
