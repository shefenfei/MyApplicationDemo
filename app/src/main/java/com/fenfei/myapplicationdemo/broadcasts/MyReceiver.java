package com.fenfei.myapplicationdemo.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class MyReceiver extends BroadcastReceiver {

    String TAG = "MyReceiver";


    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String result = intent.getExtras().getString("result");
//        Log.e(TAG, "onReceive: " + result);
        getStr(result);
    }

    public abstract void getStr(String str);

}
