package com.fenfei.myapplicationdemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class IPCService extends Service {

    private static String TAG = "IPCService";

    public IPCService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private final static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: " + "收到消息");

            Messenger messenger = msg.replyTo;
            Message message = Message.obtain();
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private static final Messenger mMessenger = new Messenger(mHandler);
}
