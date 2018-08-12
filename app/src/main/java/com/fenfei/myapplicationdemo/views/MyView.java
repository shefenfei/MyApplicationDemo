package com.fenfei.myapplicationdemo.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shefenfei on 2018/3/14.
 */

public class MyView extends View {

    private String TAG = "MyView";

    Paint mPaint;

    private LinearGradient mLinearGradient;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(20);
        mPaint.setStyle(Paint.Style.STROKE); //填充跟描边
        mPaint.setStrokeWidth(10);


        mLinearGradient = new LinearGradient(
                0,
                0,
                200,
                200,
                Color.BLUE ,
                Color.YELLOW ,
                Shader.TileMode.REPEAT);

        mPaint.setShader(mLinearGradient);
    }


    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG , "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: " );
//        return super.onTouchEvent(event);
        return true;
    }

    @TargetApi(21)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(
                0,
                0,
                100,
                100,
                90,
                90,
                true,
                mPaint
        );


        canvas.drawArc(
                100,
                100,
                200,
                200,
                90,
                90,
                false,
                mPaint
        );




        canvas.drawText("水平的字体" , 100 , 100 , mPaint);
        canvas.rotate(90);
//        canvas.save();
        canvas.drawText("竖直的字体" , 200 , 200 , mPaint);




        canvas.drawRect(0 ,0, 400, 400 ,mPaint);
    }
}
