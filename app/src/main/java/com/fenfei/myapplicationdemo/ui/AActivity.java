package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;

public class AActivity extends AppCompatActivity {

    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        TextView textView = (TextView) findViewById(R.id.jump_b);
        ImageView imageView = (ImageView) findViewById(R.id.share_a);

        textView.setOnClickListener(v -> {
            Intent intent = new Intent(AActivity.this, BActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(AActivity.this).toBundle());
        });

        //共享动画
        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(AActivity.this, BActivity.class);
            startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(
                            AActivity.this,
                            imageView,
                            "share"
                    ).toBundle()
            );
        });


    }
}
