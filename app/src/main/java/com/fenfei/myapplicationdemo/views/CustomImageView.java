package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.fenfei.myapplicationdemo.R;

/**
 * Created by shefenfei on 2017/7/20.
 */

public class CustomImageView extends View {

    private boolean mIsCircle;
    private Bitmap mImage;
    private int mImageScaleType = 0;

    private int mMeasureWidth = 0;
    private int mMeasureHeight = 0;

    private int mBitmapWidth = 0;
    private int mBitmapHeigh = 0;

    private Paint mPaint;
    private PorterDuffXfermode mXfermode;
    private Bitmap mCycleBitmap;
    private BitmapShader mBitmapShader;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);

        mIsCircle = typedArray.getBoolean(R.styleable.CustomImageView_isCircle, true);
        mImage = ((BitmapDrawable) typedArray.getDrawable(R.styleable.CustomImageView_imageSrc)).getBitmap();
        mImageScaleType = typedArray.getInt(R.styleable.CustomImageView_imageScaleType, 1);
        Log.e("CustomImageView", "CustomImageView: ");
        typedArray.recycle();

        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLUE);
        mBitmapShader = new BitmapShader(mImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasureWidth = measureWidth(widthMeasureSpec);
        mMeasureHeight = measureHeight(heightMeasureSpec);

        int finalWidth = Math.min(mBitmapWidth, mMeasureWidth);
        int finalHeight = Math.min(mBitmapHeigh, mMeasureHeight);

        setMeasuredDimension(
                finalWidth,
                finalHeight
        );
    }


    private int measureWidth(int widthMeasureSpec) {
        int width = 0;

        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            width = (int) convertPixelsToDp(300);
            if (mode == MeasureSpec.AT_MOST) {
                width = Math.min(width, size);
            }
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int height = 0;

        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            height = (int) convertPixelsToDp(300);
            if (mode == MeasureSpec.AT_MOST) {
                height = Math.min(height, size);
            }
        }
        return height;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mImage != null) {
            mBitmapWidth = mImage.getWidth();
            mBitmapHeigh = mImage.getHeight();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        canvas.drawCircle(
                200,
                200,
                100,
                mPaint);
                */

        canvas.drawRect(
                10,
                10,
                200,
                200,
                mPaint);

    }

    private float getRadius(int measureWidth, int measureHeight) {
        int min = Math.min(measureWidth, measureHeight);
        return min / 2;
    }

    //px2dp
    private float convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    //dp2px
    private float convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
