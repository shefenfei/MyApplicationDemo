package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ZoomButton;

import com.fenfei.myapplicationdemo.R;

public class BActivity extends AppCompatActivity {

    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_b);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("toolbar");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(item -> handleMenuClick(item));
    }

    @TargetApi(21)
    private boolean handleMenuClick(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                msg += "Click edit";
                buildNotifation(msg);
                break;
            case R.id.menu_item_search:
                msg += "Click share";
                break;
        }
        return true;
    }

    private void buildNotifation(String msg) {
        Notification.Builder builder = new Notification.Builder(this);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com")), 0);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentText(msg);
        builder.setContentTitle("title");

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.getNotification());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_share_menu, menu);
        return true;
    }

    private void testClass() {
        ZoomButton zoomButton;
    }
}
