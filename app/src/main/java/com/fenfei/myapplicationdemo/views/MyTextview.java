package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by shefenfei on 2017/1/23.
 */
public class MyTextview extends Button {

    private String TAG = this.getClass().getSimpleName();

    public MyTextview(Context context) {
        this(context, null);
    }

    public MyTextview(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public MyTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        CardView cardView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean intercept = false;
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: " + "ACTION_DOWN");
                intercept = true;
                return intercept;
//                break;

            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: " + "ACTION_MOVE");
                intercept = false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: " + "ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "onTouchEvent: " + "ACTION_CANCEL");
        }
        return super.onTouchEvent(event);
//        return intercept;
    }

    /*
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(event);    //事件会自动的分发给当前 View 的 onInterceptTouchEvent 方法 在自定义view中并不会有这个方法
//        return true; //直接返回true 代表我将直接对事件消费,并不会向下传递,不会到下面的onTouchEvent();
    }

    */




}
