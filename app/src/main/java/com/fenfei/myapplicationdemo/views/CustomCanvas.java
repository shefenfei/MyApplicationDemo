package com.fenfei.myapplicationdemo.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.fenfei.myapplicationdemo.R;

import java.io.InputStream;

/**
 * Created by shefenfei on 2016/11/10.
 */

public class CustomCanvas extends View implements GestureDetector.OnGestureListener {

    private Paint mPaint;
    private Bitmap mBitmap;

    private Rect mRectSrc;
    private Rect mRectDst;

    private GestureDetectorCompat mDetectorCompat_;

    private VelocityTracker mVelocityTracker_;

    public CustomCanvas(Context context) {
        this(context, null);
    }

    public CustomCanvas(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initListener(context);
    }

    private void initListener(Context context) {
        mDetectorCompat_ = new GestureDetectorCompat(context, new MyGestureListener());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    //初始化画笔
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        mRectSrc = new Rect(0, 0, 100, 100);
        mRectDst = new Rect(10, 10, 100, 500);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
    }

    @TargetApi(21)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(mBitmap, mRectSrc, mRectDst, mPaint);
//
//        canvas.drawRect(mRectSrc, mPaint);
//
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);
//        float cx = mRectSrc.width() / 2;
//        float cy = mRectSrc.height() / 2;
//        float mRadius = Math.min(cx, cy);
//        canvas.drawCircle(cx, cy, mRadius, mPaint);
//
//
//        canvas.drawLine(200f, 200f, 700f, 700f, mPaint);
//        canvas.drawLine(700f, 700f, 300f, 200f, mPaint);
//        canvas.drawLine(300f, 200f, 200f, 200f, mPaint);
//
//
//        canvas.drawOval(200f, 200f, 300f, 400f, mPaint);
//        canvas.save();
//        canvas.drawText("测试", 200f, 200f, mPaint);
//
//        canvas.restore();
//        canvas.rotate(90f);
//        canvas.drawText("测试123", 300f, 300f, mPaint);
//
//        int left = getLeft();
//        int top = getTop();
//        int right = getRight();
//        int bottom = getBottom();
//        Log.e("x:y", "onCreate: " + left + "..." + top + "..." + right + ".." + bottom);


        clipImage(canvas);


    }

    private void clipImage(Canvas canvas) {

        InputStream inputStream = getResources().openRawResource(R.raw.icon_1);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        canvas.save();
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, width, height, mPaint);
        canvas.restore();
        canvas.save();

        canvas.clipRect(0, 0, width, height * 3 / 2);
        canvas.drawBitmap(bitmap, width, height, mPaint);
        canvas.restore();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetectorCompat_.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        //down
        @Override
        public boolean onDown(MotionEvent e) {
            if (mVelocityTracker_ == null) {
                mVelocityTracker_ = VelocityTracker.obtain();
            } else {
                mVelocityTracker_.clear();
            }
            mVelocityTracker_.addMovement(e);
            return super.onDown(e);
        }

        //up
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mVelocityTracker_.addMovement(e1);
            mVelocityTracker_.computeCurrentVelocity(1000);
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        //move
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mVelocityTracker_.recycle();
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}
