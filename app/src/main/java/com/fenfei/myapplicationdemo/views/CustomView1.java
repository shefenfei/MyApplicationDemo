package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.utils.DensityUtils;

/**
 * 自定义viewgroup----学习measure,layout的使用,并特别要学习LayoutParams的使用方法
 * Created by shefenfei on 2016/11/16.
 */
public class CustomView1 extends ViewGroup {


    private String TAG = "CustomView1";

    public CustomView1(Context context) {
        super(context);
    }

    public CustomView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //测量Viewgroup或view自己的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.e(TAG, "onMeasure: getPaddingLeft" + getPaddingLeft()  + "..." + getPaddingRight());

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        Log.e(TAG, "onMeasure: width-mode " + widthMode);
        Log.e(TAG, "onMeasure: width " + widthSize);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.e(TAG, "onMeasure: height-mode " + heightMode);
        Log.e(TAG, "onMeasure: height " + heightSize);

        //测量出这个父类的viewgroup的大小
        float w = DensityUtils.convertPixelsToDp(widthSize);
        float h = DensityUtils.convertPixelsToDp(heightSize);

        int childTotalHeight = 0;

        ListView listView;

        //使用getMeasuredHeight();方法进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Log.e(TAG, "onMeasure: padding" + i + ".." + child.getPaddingLeft() );
            childTotalHeight += child.getMeasuredHeight();
        }

        Log.e(TAG, "onMeasure: childTotalHeight" + childTotalHeight);
        Log.e(TAG, "onMeasure: childTotalHeight" + DensityUtils.convertPixelsToDp(childTotalHeight));

        if (DensityUtils.convertPixelsToDp(childTotalHeight) > h) {
            setMeasuredDimension(widthMeasureSpec, childTotalHeight);
        } else {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }
        Log.e(TAG, "onMeasure: " + getHeight() + " ..." + DensityUtils.convertPixelsToDp(getHeight()));
        Log.e(TAG, "onMeasure: " + w + "..." + h);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (changed) {
            for (int i = 0; i < childCount; i++) {
                final View childView = getChildAt(i);
                if (childView instanceof TextView) {
                    //如果View在布局文件中已经申明了,这里的params在不做特别处理的时候,是拿到view在xml中的值,
                    //注意:这个取到值是以px为单位的
                    LayoutParams params = (LayoutParams) childView.getLayoutParams();
                    Log.e(TAG, "onLayout: " +
                            " left:" + params.leftMargin +
                            " --right : " + params.rightMargin +
                            " top :" + params.topMargin +
                            " bottom " + params.bottomMargin
                    );
                    if (i == 2) {
                        params.leftMargin = (int) DensityUtils.convertDpToPixel(30);
                    }

                    ((TextView) childView).setGravity(Gravity.CENTER);
                    childView.layout(0 + params.leftMargin ,
                            i * childView.getMeasuredHeight() ,
                            childView.getMeasuredWidth() + params.leftMargin ,
                            (i + 1) * childView.getMeasuredHeight());
                }
            }
        }
    }

    //===================

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomView1.LayoutParams(getContext() ,attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    //===================

    //自定义viewgroup中的layoutParams
    static class LayoutParams extends MarginLayoutParams {

        //处理这个核心方法
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            init();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            init();
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            init();
        }

        private void init() {
            leftMargin = (int) DensityUtils.convertDpToPixel(20);
        }
    }
}
