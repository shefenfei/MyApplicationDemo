package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by shefenfei on 2017/1/23.
 */

public class TouchLinnearLayout extends LinearLayout {

    private String TAG = this.getClass().getSimpleName();

    private float mDownX;
    private float mDownY;

    private float mCurrX;
    private float mCurrY;

    public TouchLinnearLayout(Context context) {
        this(context , null);
    }

    public TouchLinnearLayout(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public TouchLinnearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent: " + "ACTION_DOWN");

//                return true;
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent: " + "ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent: " + "ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);   //走默认的事件逻辑
//        return true; //不向下传递并自己处理,不向onInterceptTouchEvent() ,onTouchEvent()传递
//        return false; //不再向下传递并不消费事件,返回上层
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onInterceptTouchEvent: " + "ACTION_DOWN");
//                return true;
                mDownX = ev.getX();
                mDownY = ev.getY();

                mCurrX = mDownX;
                mCurrY = mDownY;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onInterceptTouchEvent: " + "ACTION_MOVE");
                mCurrX = ev.getX();
                mCurrY = ev.getY();
                if (mCurrY - mDownY > 1000) {
                    Log.e(TAG, "onInterceptTouchEvent: " + "执行大于条件" );
                    return true;
                }
//                return true; //在条件成立,拦截move事件
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onInterceptTouchEvent: " + "ACTION_UP");
                break;
        }

        return super.onInterceptTouchEvent(ev);
//        return false;
//        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ViewGroup parent;
        View child;
        Rect touchArea;
//        parent.setTouchDelegate( new TouchDelegate(touchArea, child) );

        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: " + "ACTION_DOWN");
                return true;
//                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: " + "ACTION_MOVE");
                return true;
//                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: " + "ACTION_UP");
                break;
        }

        return super.onTouchEvent(event); //返回这个值,如果在这个方法中,不拦截任何事件的话,走默认的,后续的move , up不在继续进入
//        return true;   //子view不拦截 ,我全拦截
    }


    @Override
    public void computeScroll() {
        super.computeScroll();

    }
}
