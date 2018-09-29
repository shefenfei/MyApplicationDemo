package com.fenfei.myapplicationdemo.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.fenfei.myapplicationdemo.interfaces.Contants;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.fenfei.myapplicationdemo.services.action.FOO";
    public static final String ACTION_BAZ = "com.fenfei.myapplicationdemo.services.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.fenfei.myapplicationdemo.services.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.fenfei.myapplicationdemo.services.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            intent.setAction(Contants.BROAD_CAST);
            for (int i = 0; i < 10; i++) {
                intent.putExtra("result", "hello local broad" + i);
                try {
                    Thread.sleep(1000);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
