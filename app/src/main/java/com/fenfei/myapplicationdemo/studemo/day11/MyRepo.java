package com.fenfei.myapplicationdemo.studemo.day11;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by shefenfei on 2018/1/23.
 */
public class MyRepo {

    String TAG = "MyRepo";

    MyService mMyService;

    @Inject
    public MyRepo(MyService service) {
        this.mMyService = service;
    }

    public void testMsg() {
        Log.e(TAG, "testMsg: " );

        mMyService.getService();
    }
}
