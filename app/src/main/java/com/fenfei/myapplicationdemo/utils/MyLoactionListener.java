package com.fenfei.myapplicationdemo.utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by shefenfei on 2017/11/8.
 */

public class MyLoactionListener implements LifecycleObserver {

    private boolean flag = false;
    private Lifecycle mLifecycle;

    public MyLoactionListener(Context context , Lifecycle lifecycle) {
        // ...
        this.mLifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        // connect to system location service
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        // disconnect from system location service
    }


    public void enable() {
        flag = true;
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {

        }
    }



    private String formatParams(Map<String , Object> params) {
        Set<String> ks = new HashSet<>(params.keySet());
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key);
        }

        String p1 = "username";
        String s = new String(p1.getBytes(), Charset.forName("US-ASCII"));
        System.out.println(s);

        return "";
    }

}
