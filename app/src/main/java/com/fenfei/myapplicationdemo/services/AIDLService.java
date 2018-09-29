package com.fenfei.myapplicationdemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AIDLService extends Service {

    public AIDLService() {

    }

//    private Binder mBinder_ = new IShopInterface.Stub() {};

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
