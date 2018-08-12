package com.fenfei.myapplicationdemo.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.fenfei.myapplicationdemo.R;

/**
 * Created by shefenfei on 2018/3/19.
 */

@SuppressLint("AppCompatCustomView")
public class ColorMatrixDemoView extends ImageView {

    private Paint mPaint;
    private Bitmap mBitmap;
    ColorMatrix colorMatrix;
    PorterDuffXfermode porterDuffXfermode;

    public ColorMatrixDemoView(Context context) {
        super(context);
    }

    public ColorMatrixDemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        mBitmap = BitmapFactory.decodeResource(context.getResources() , R.drawable.testdemo);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    public ColorMatrixDemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth() , mBitmap.getHeight() , Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap);

//        canvas.drawRoundRect(0, 0 , mBitmap.getWidth() , mBitmap.getHeight() , 80, 80 , mPaint);
        canvas.drawCircle(50 , 50, 50 , mPaint);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(mBitmap , 0, 0 ,mPaint);
    }
}
