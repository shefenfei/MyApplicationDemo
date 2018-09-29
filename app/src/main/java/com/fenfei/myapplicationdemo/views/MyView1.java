package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fenfei.myapplicationdemo.utils.DensityUtils;

/**
 * Created by shefenfei on 2016/12/14.
 */

public class MyView1 extends View {

    private String TAG = "MyView1";

    private Paint mPaint;

    private int mDefaultViewWidth = (int) DensityUtils.convertDpToPixel(200);
    private int mDefaultViewHeight = (int) DensityUtils.convertDpToPixel(200);
    ;

    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "MyView1: 构造函数");
        mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1, 0, 0, 0, 100,
//                0, 0, 0, 0, 100,
//                0, 0, 0, 0, 0,
//                0, 0, 0, 0, 0
//        });
//
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");

        float radius = DensityUtils.convertDpToPixel(100);


        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawLines(new float[]{
                0, 0, 50, 40,
                0, 0, 100, 200
        }, mPaint);
        canvas.drawCircle(0, 0, radius, mPaint);
        canvas.save();

        //画四个刻度
        for (int i = 0; i < 60; i++) {
            canvas.drawLine(radius - 90, 0, radius, 0, mPaint);
            canvas.drawText(String.valueOf(i), radius - 80, 0, mPaint);
            canvas.rotate(6);
        }




    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "onDetachedFromWindow: ");
    }
}
