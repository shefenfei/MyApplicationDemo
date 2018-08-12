package com.fenfei.myapplicationdemo.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shefenfei on 2018/3/20.
 */

public class PathEffectView extends View {

    private PathEffect mPathEffect;
    private Paint mPaint;
    private Path mPath;

    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPath.lineTo(0, 50);
        for (int i = 0; i < 30; i++) {
            mPath.lineTo(i * 30, (float) Math.random() * 100);
        }

//        CornerPathEffect
//        DiscretePathEffect;
//        DashPathEffect;
//        PathDashPathEffect;
//        ComposePathEffect;

        mPathEffect = new CornerPathEffect(8);
        mPaint = new Paint();
        mPaint.setPathEffect(mPathEffect);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.GREEN);

    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void test() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setTarget(this);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animation.getAnimatedValue();
            }
        });

        this.animate()
                .alpha(0)
                .y(300)
                .setDuration(300)
                .withStartAction(() -> {

                }).withEndAction(() -> {

                }).start();
    }
}
