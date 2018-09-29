package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by shefenfei on 2017/1/23.
 */

public class SlidingMenuLayout extends HorizontalScrollView {

    private int mScreenWidth;
    private int mMenuWidth;
    private int mHalfMenuWidth;
    private int mMenuPaddingRight = 50;

    private boolean isShow;

    public SlidingMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenWidth = displayMetrics.widthPixels;
        Log.e("SlidingMenuLayout", "SlidingMenuLayout: " + mScreenWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        LinearLayout parent = (LinearLayout) getChildAt(0);
        LinearLayout childMenu = (LinearLayout) parent.getChildAt(0);
        LinearLayout chileContent = (LinearLayout) parent.getChildAt(1);

        float density =  this.getContext().getResources().getDisplayMetrics().density;
        mMenuPaddingRight = (int) (mMenuPaddingRight * density + 0.5f * (mMenuPaddingRight > 0 ? 0 : 1));

        mMenuPaddingRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMenuPaddingRight,
                this.getContext().getResources().getDisplayMetrics());

        Log.e("width", "onMeasure: " + mMenuPaddingRight);

        mMenuWidth = mScreenWidth - mMenuPaddingRight;
        mHalfMenuWidth = mMenuWidth / 2;
        childMenu.getLayoutParams().width = mMenuWidth;
        chileContent.getLayoutParams().width = mScreenWidth;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!isShow) {
            this.scrollTo(-mMenuWidth, 0);
            isShow = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX > mHalfMenuWidth) {
                    this.smoothScrollTo(mMenuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return super.onTouchEvent(ev);
    }

}
