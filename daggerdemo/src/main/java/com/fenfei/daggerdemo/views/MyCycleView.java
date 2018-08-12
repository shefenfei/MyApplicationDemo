package com.fenfei.daggerdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shefenfei on 2018/3/14.
 */

public class MyCycleView extends View {

    //画圆
    private Paint mCyclePaint;
    private Paint mTextPaint;
    private int length;

    private RectF mRectF;

    private String mText = "hahhaha";


    public MyCycleView(Context context) {
        this(context , null);
    }

    public MyCycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public MyCycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCyclePaint = new Paint();
        mCyclePaint.setColor(Color.BLUE);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);

        mRectF = new RectF(200 , 200 , 500, 500);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2 , 100 , mCyclePaint);
        canvas.drawArc(mRectF , 0 , 270 , true , mCyclePaint);
        canvas.drawText(mText , getMeasuredWidth() / 2 , getMeasuredHeight() / 2 , mTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
