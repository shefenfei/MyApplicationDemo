package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;

import java.util.Arrays;

/**
 * Android中的3DTouch Api
 * @see //developer.android.google.cn/reference/android/content/pm/ShortcutManager.html
 */
public class ShortCutActivity extends AppCompatActivity {

    String TAG = "ShortCutActivity";

    @TargetApi(25)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_short_cut);
        int c = frameLayout.getChildCount();
        for (int i=0;i<c; i++) {
            TextView t = (TextView) frameLayout.getChildAt(i);
            String text = t.getText().toString();
            Log.e(TAG, "第 " + i + "是" + text);
        }

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        int count = shortcutManager.getMaxShortcutCountPerActivity();
        Log.e(TAG, "onCreate: " + count);

        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Web site")
                .setLongLabel("Open the web site")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.baidu.com/")))
                .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));

    }
}
