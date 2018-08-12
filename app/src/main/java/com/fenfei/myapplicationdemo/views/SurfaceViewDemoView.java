package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by shefenfei on 2018/3/20.
 *  创建一个自定义的surfaceView 时要继承SurfaceView 并实现两个现有的接口，SurfaceHolder.Callback , Runnable
 */
public class SurfaceViewDemoView extends SurfaceView implements SurfaceHolder.Callback , Runnable {

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing = true;
    private Path mPath;
    private Paint mPaint;

    private int x;
    private int y;


    public SurfaceViewDemoView(Context context) {
        super(context);
    }

    public SurfaceViewDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLUE);
        mPath = new Path();
    }

    public SurfaceViewDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //创建时
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(this).start();
    }

    //更新时
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //销毁时
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x += 1;
            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x , y);
        }
    }


    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawPath(mPath , mPaint);
        }catch (Exception e) {

        }finally {
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
