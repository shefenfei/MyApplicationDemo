package com.fenfei.daggerdemo.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shefenfei on 2018/3/13.
 */

@SuppressLint("AppCompatCustomView")
public class MyView extends TextView {

    private Paint mPaint1;
    private Paint mPaint2;

    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //画之前做事情
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
//        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
//        canvas.save();
//        canvas.translate(10, 0);
        super.onDraw(canvas);
        //画之后做事情
//        canvas.restore();
        if (mMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }

            mMatrix.setTranslate(mTranslate , 0);
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(100);
        }

    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
                        new int[]{Color.BLUE, 0xffffff, Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP
                );

                mMatrix = new Matrix();
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec)
        );
    }

    //对宽进行测试计算
    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY: //精准模式
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST: // wrap_content 模式下要有默认值
                width = 200;
                width = Math.min(width, widthSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;

        }
        return width;
    }

    //对高进测量
    private int measureHeight(int heightMeasureSpec) {
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY: //精准模式
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST: // wrap_content 模式下要有默认值
                height = 200;
                height = Math.min(height, heightSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;

        }
        return height;
    }
}
