package com.fenfei.myapplicationdemo.studemo.day11;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by shefenfei on 2018/1/23.
 */

public class MyService {

    @Inject
    public MyService() {
    }

    public void getService() {
        Log.e("MyService", "getService: " );
    }
}
