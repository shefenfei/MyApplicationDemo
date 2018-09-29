package com.fenfei.myapplicationdemo.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.fenfei.myapplicationdemo.R;

public class CircleDemoActivity extends AppCompatActivity {

    private String TAG = "CircleDemoActivity";
    private CircleLayout mCircleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_demo);

        mCircleLayout = (CircleLayout) findViewById(R.id.circle_layout_1);
        //最顶上item点击事件
        mCircleLayout.setOnItemClickListener((view -> {
            Log.e(TAG, "onItemClick:" + view.getId());
            if (view.getId() == R.id.menu1) {
                Log.e(TAG, "onCreate: " + view.getId() );
            }
            if (view.getId() == R.id.menu2) {
                Log.e(TAG, "onCreate: " + view.getId() );
            }
            if (view.getId() == R.id.menu3) {
                Log.e(TAG, "onCreate: " + view.getId() );
            }
            if (view.getId() == R.id.menu4) {
                Log.e(TAG, "onCreate: " + view.getId() );
            }
            if (view.getId() == R.id.menu5) {
                Log.e(TAG, "onCreate: " + view.getId() );
            }
        }));

        //layout布局中的点击事件
        mCircleLayout.setOnCenterClickListener(()-> {
            Log.e(TAG, "onCenterClick:" );
        });

        //当layout中的item 旋转的过程会时时调用
        mCircleLayout.setOnItemSelectedListener((view)->{
            Log.e(TAG, "onItemSelectd: ");
        });

        //当旋转结束的时候调用
        mCircleLayout.setOnRotationFinishedListener((view)->{
            Log.e(TAG, "onRotationFinished: " );
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(R.drawable.finance);
            }
        });
    }
}
