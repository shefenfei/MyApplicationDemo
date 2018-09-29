package com.fenfei.myapplicationdemo.studemo.day09;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

/**
 * Created by shefenfei on 2018/1/15.
 * life生命周期组件 support 26.0.1 以后的库都自动实现LifecycleOwner
 */
public class MyObserver implements LifecycleObserver {

    private String TAG = "MyObserver:";
    private Context mContext;
    private Lifecycle mLifecycle;

    public MyObserver(Context context , Lifecycle lifecycle ) {
        this.mContext = context;
        this.mLifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e(TAG, "connectListener:  activity 处于ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectListener() {
        Log.e(TAG, "connectListener:  activity 处于ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.e(TAG, "disconnectListener: activity 处于ON_PAUSE : getCurrentState");
        }
        Log.e(TAG, "disconnectListener: activity 处于ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory() {
        Log.e(TAG, "destory: activity 处于ON_DESTROY");
    }

}
