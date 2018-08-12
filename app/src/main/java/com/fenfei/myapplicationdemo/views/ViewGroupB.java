package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by shefenfei on 2018/3/14.
 */

public class ViewGroupB extends LinearLayout {

    private String TAG = "ViewGroupB";

    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: "  );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent: " );
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
