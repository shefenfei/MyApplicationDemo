package com.fenfei.myapplicationdemo.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by shefenfei on 2017/3/15.
 * 仿美团购物车
 */
public class ShopButton extends View {

    private String TAG = "ShopButton";

    private OnAddListener mListener_;

    private Paint mPaintBg;
    private Paint mPaintShape;
    private Paint mPaintText;

    private boolean isShow = true;

    private int mDefaultWidth = 100;
    private int mDefaultHeight = 50;

    private float rx = 20;
    private float ry = 20;

    private int reduceRegion = 1; //减号的区域
    private int plusRegion = 2; //加号的区域

    private RectF mRect_;
    private RectF mRectText;
    private String text = "加入购物车";

    private int count = 1;

    public ShopButton(Context context) {
        this(context, null);
    }

    public ShopButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShopButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);

        mPaintShape = new Paint();
        mPaintShape.setAntiAlias(true);
        mPaintShape.setStyle(Paint.Style.STROKE);
        mPaintShape.setColor(Color.BLACK);

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mDefaultWidth = measureWidth(widthMeasureSpec);
        mDefaultHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(mDefaultWidth, mDefaultHeight);
    }

    //测量宽
    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            width = mDefaultWidth;
        }
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        }
        return width;
    }

    //测量高
    private int measureHeight(int heightMeasureSpec) {
        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            height = mDefaultHeight;
        }
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        }
        return height;
    }

    private int measureTextLegth() {
        int len = (int) mPaintBg.measureText(text);
        Log.e(TAG, "measureTextLegth: " + len);
        return len;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShow) { //默认状态
            mPaintBg.setColor(Color.YELLOW);
            mRect_ = new RectF(0, 0, mDefaultWidth, mDefaultHeight);
            canvas.drawRoundRect(mRect_, rx, ry, mPaintBg);

            mPaintBg.setColor(Color.RED);
            mPaintBg.setTextSize(30);
            int baseX = mDefaultWidth / 2 - measureTextLegth() / 2;
            int baseY = (int) (mDefaultHeight / 2 - ((mPaintBg.descent() + mPaintBg.ascent()) / 2));
            canvas.drawText(text, baseX, baseY, mPaintBg);
        } else {
            RectF layoutRect = new RectF(0, 0, mDefaultWidth, mDefaultHeight);
            mPaintBg.setColor(Color.WHITE);
            canvas.drawRect(layoutRect, mPaintBg);
            int radius = (int) (layoutRect.height() / 2);

            float firstCircleX = radius;
            float secondCircleX = mDefaultWidth - radius;
            float circley = mDefaultHeight / 2;

            canvas.drawCircle(firstCircleX, circley, radius, mPaintShape);
            //画减号
            canvas.drawLine(radius / 2, circley, radius / 2 + radius, circley, mPaintShape);
//            mRectText = new RectF(mDefaultHeight , 0 , (mDefaultWidth - mDefaultHeight) , mDefaultHeight);
//            canvas.drawRect(mRectText, mPaintText);
            drawNumber(canvas, count);
            canvas.drawCircle(secondCircleX, circley, radius, mPaintShape);
            //画加号
            canvas.drawLine(secondCircleX - radius / 2, circley, secondCircleX - radius / 2 + radius, circley, mPaintShape);
            canvas.drawLine(secondCircleX, radius / 2, secondCircleX, radius + radius / 2, mPaintShape);
        }
    }

    private void drawNumber(Canvas canvas , int count) {
        mPaintText.setTextSize(40);
        int width = mDefaultWidth;
        int height = mDefaultHeight;

        int baseX = (int) (width / 2 - mPaintText.measureText(String.valueOf(count)) / 2);
        int baseY = (int) (height / 2 - ((mPaintText.descent() + mPaintText.ascent()) / 2));

        canvas.drawText(String.valueOf(count) , baseX , baseY , mPaintText);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                int area = 0;
                if (!isShow) {
                    float x = event.getX();
                    float y = event.getY();
                    if (x >= 0 && x < mDefaultHeight && y > 0 && y< mDefaultHeight) {
                        area = reduceRegion;
                    }else if (x > (mDefaultWidth-mDefaultHeight) && x < mDefaultWidth && y > 0 && y< mDefaultHeight) {
                        area = plusRegion;
                    }
                    Log.e(TAG, "onTouchEvent: " + area);

                    if (area == reduceRegion) { //点击减号
                        count --;
                        mListener_.onReduce();
                        if (count <= 0) {
                            isShow = true;
                            count = 1;
                            animateView();
                        }
                    }else if (area == plusRegion){ //点击加号
                        count ++;
                        mListener_.onAdd();
                    }
                }else {
                    isShow = false;
                    animateView();
                }
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
        }

        return super.onTouchEvent(event);
    }

    private void animateView() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this , "rotationY" , 0F , 360F);
        animator.setDuration(2000);
        animator.start();
    }

    public void setOnAddListener(OnAddListener listener) {
        mListener_ = listener;
    }

    public interface OnAddListener {
        void onAdd();

        void onReduce();
    }
}
