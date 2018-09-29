package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.TextView;

/**
 * Created by shefenfei on 2016/11/11.
 */

public class CustomViewgroup extends LinearLayout {

    private String TAG = "customViewgroup";
    OverScroller mScroller ;
    Paint paint;
    Rect rect;

    private int mTouchSlop;

    public CustomViewgroup(Context context) {
        super(context);
    }

    public CustomViewgroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        //默认情况下viewgroup是不会调用onDraw()方法的。
        //有两种方法可以解决 : 1 ,设置自定义viewgroup的背景  2, setWillNotDraw(false) 标记重置为false
        setWillNotDraw(false);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new OverScroller(context);
        paint = new Paint();
        paint.setStrokeWidth(10.0f);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);

        rect = new Rect(0 , 0, 200 ,200);
    }

    public CustomViewgroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    //测量宽
    private int measureWidth(int widthMeasureSpec) {
        int w ;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) { // 当specMode为EXACTLY时，直接使用指定的specSize即可
            w = width;
        }else { // 当specMode为其他两种模式时，需要给它一个默认的大小
            w = 200;
            if (widthMode == MeasureSpec.AT_MOST) {
                w = Math.min(w , width);
            }
        }
        return w;
    }

    //测量高
    private int measureHeight(int heightMeasureSpec) {
        return heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView =  getChildAt(i);
            childView.layout(i * 100 , i * 100 ,200 * (i + 1) ,200 * (i + 1));
        }
    }

    //画子View
    @Override
    protected void dispatchDraw(Canvas canvas) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView tv = (TextView) getChildAt(i);
            canvas.drawRect(rect , paint);
            canvas.save();
//            tv.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    //画自己
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, TAG + ":onTouchEvent() ");
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                float deltaY = event.getRawY() - mTouchSlop;

                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
