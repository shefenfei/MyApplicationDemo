package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.views.CustomViewgroup;

public class CustomGroupActivity extends AppCompatActivity {

    private String DEBUG_TAG = "CustomGroupActivity";
    private TextView mTextView_;

    private Button mButton_scrollTo;
    private Button mButton_scrollBy;

    private CustomViewgroup mCustomViewgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_group);


        mCustomViewgroup = (CustomViewgroup) findViewById(R.id.viewgroup);
        mButton_scrollBy = (Button) findViewById(R.id.scroll_by);
        mButton_scrollTo = (Button) findViewById(R.id.scroll_to);

        mButton_scrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomViewgroup.scrollBy(0,-10);
            }
        });

        mButton_scrollTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomViewgroup.scrollTo(-10,-10);
            }
        });



        mTextView_ = (TextView) findViewById(R.id.tv_click);
        mTextView_.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.d(DEBUG_TAG, "Action was DOWN");
                        return false;
                    case (MotionEvent.ACTION_MOVE):
                        Log.e(DEBUG_TAG, "Action was move ");
                        return true;
                    case (MotionEvent.ACTION_UP):
                        Log.d(DEBUG_TAG, "Action was UP");
                        return true;
                    case (MotionEvent.ACTION_CANCEL):
                        Log.d(DEBUG_TAG, "Action was CANCEL");
                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):
                        Log.d(DEBUG_TAG, "Movement occurred outside bounds " +
                                "of current screen element");
                        return false;
                }
                return false;
            }
        });
    }
}

