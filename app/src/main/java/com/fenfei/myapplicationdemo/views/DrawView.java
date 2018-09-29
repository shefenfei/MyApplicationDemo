package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shefenfei on 2017/1/26.
 */

public class DrawView extends View {

    Paint mPaint_;
    Path path;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint_ = new Paint();
        mPaint_.setColor(Color.RED);
        mPaint_.setStyle(Paint.Style.STROKE);
        mPaint_.setAntiAlias(true);
        path = new Path();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2 , getHeight() / 2);

        canvas.drawLine(-getWidth() / 2 , 0 , getWidth() , 0 , mPaint_);
        canvas.drawLine(0 , -getHeight() / 2 , 0 , getHeight() , mPaint_);

        path.addRect(-200 , -200 , 200 , 200 , Path.Direction.CCW);
        path.setLastPoint(-300 , 300);
        canvas.drawPath(path , mPaint_);
    }
}
