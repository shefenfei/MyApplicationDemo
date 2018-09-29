package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by shefenfei on 2017/1/24.
 */

public class ScrollerLayout extends LinearLayout {

    private String TAG = this.getClass().getSimpleName();

    private OverScroller mScroller_;
    private ViewConfiguration mConfiguration_;
    private int mSlop; //最小的滑动条件

    private float mDownX; //初始按下的x距离
    private float mLastX; //手指离开的x距离

    public ScrollerLayout(Context context) {
        this(context, null);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        mScroller_ = new OverScroller(context,
                new AccelerateDecelerateInterpolator(context, attrs));
        mConfiguration_ = ViewConfiguration.get(context);
        mSlop = mConfiguration_.getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // TODO: 2017/1/24  处理布局逻辑
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(
                    i * childView.getMeasuredWidth(),
                    0,
                    (i + 1) * childView.getMeasuredWidth(),
                    childView.getMeasuredHeight()
            );
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getRawX();
                mLastX = mDownX;
                break;
            case MotionEvent.ACTION_MOVE:

                Log.e(TAG, "onInterceptTouchEvent: " + ev.getRawX());
                mLastX = ev.getRawX();
                float scrollX = Math.abs(mLastX - mDownX);
                if (scrollX > mSlop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN: //事件开始的地方,如果处理,一定要拦截
                Log.e(TAG, "onTouchEvent: " + " Down :触发" + event.getRawX());
                mDownX = event.getRawX();
                mLastX = mDownX;
                return true;
//                break;

            case MotionEvent.ACTION_MOVE:
                mLastX = event.getRawX();
                Log.e(TAG, "onTouchEvent: " + "触发" + event.getRawX());
                float scrollX = mLastX - mDownX; //目标要移动多少
                scrollBy((int) scrollX, 0);
                return true;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: " + event.getRawX() + ": scrollX =" + getScrollX() + " : scrollY :" + getScrollY());
                mScroller_.startScroll(getScrollX(), 0, (int) (mLastX - mDownX), 0, 1000);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller_.computeScrollOffset()) {
            scrollTo(mScroller_.getCurrX(), mScroller_.getCurrY());
            invalidate();
        }
    }
}
