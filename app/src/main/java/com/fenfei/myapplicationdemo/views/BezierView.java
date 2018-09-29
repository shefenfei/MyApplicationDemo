package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by shefenfei on 2017/4/5.
 */
public class BezierView extends View {

    Scroller mScroller;

    ViewGroup.MarginLayoutParams mLayoutParams;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int lastX = 0;
        int lastY = 0;

        //使用视图坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;


                /*
                //使用offsetLeftAndRight() 和 offsetTopAndBottom()
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                */

                /*
                mLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                mLayoutParams.leftMargin = getLeft() + offsetX;
                mLayoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(mLayoutParams);
                */

                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                /*
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                        */

//                lastX = x;
//                lastY = y;
                break;

            case MotionEvent.ACTION_UP:
                ViewGroup viewGroup = (ViewGroup) getParent();
                mScroller.startScroll(
                        viewGroup.getScrollX(), // 我们获取子View的移动距离，getScrollX(), getScrollY()
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY()
                );
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
        invalidate();
    }
}
