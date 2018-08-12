package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fenfei.myapplicationdemo.R;

/**
 * Created by shefenfei on 2018/3/20.
 */

public class ReflectView extends View {

    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Bitmap mSrcBitmap , mRefBitmap;

    public ReflectView(Context context) {
        super(context);
    }

    public ReflectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();

        mSrcBitmap = BitmapFactory.decodeResource(getResources() , R.drawable.testdemo);
        //copy bitmap

        Matrix matrix = new Matrix();
        matrix.setScale(1F , -1F);

        mRefBitmap = Bitmap.createBitmap(
                mSrcBitmap,
                0 ,
                0 ,
                mSrcBitmap.getWidth() ,
                mSrcBitmap.getHeight() ,
                matrix ,
                true);

        mLinearGradient = new LinearGradient(
                0 ,
                mSrcBitmap.getHeight() ,
                0,
                mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4 ,
                0XDD000000,
                0XDD100000,
                Shader.TileMode.CLAMP

        );
        mPaint.setShader(mLinearGradient);

    }

    public ReflectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mSrcBitmap , 0 , 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight() , null);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        canvas.drawRect(0 , mSrcBitmap.getHeight() , mRefBitmap.getWidth() , mSrcBitmap.getHeight() * 2 , mPaint);
        mPaint.setXfermode(null);


//        CornerPathEffect;
//        DiscretePathEffect;
//        DashPathEffect;
//        PathDashPathEffect;
//        ComposePathEffect;
    }
}
