package com.fenfei.daggerdemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fenfei.daggerdemo.R;

/**
 * Created by shefenfei on 2018/3/13.
 */

public class MyViewGroup extends ViewGroup {

    private String DEFAULT_CENTER_TITLE = "TITLE";
    private String DEFAULT_RIGHT_TITLE = "RIGHT";

    private String centerTitle;
    private String rightTitle;
    private Drawable leftIcon;

    private ImageView mLeftIconImage;
    private TextView mCenterView;
    private TextView mRightView;


    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array
                = context.obtainStyledAttributes(attrs , R.styleable.MyViewGroup , defStyleAttr , 0);

        centerTitle = array.getString(R.styleable.MyViewGroup_centerTitle);
        rightTitle = array.getString(R.styleable.MyViewGroup_rightTitle);
        leftIcon = array.getDrawable(R.styleable.MyViewGroup_leftIcon);
        array.recycle();

        //add View
        mLeftIconImage = new ImageView(context);
        mLeftIconImage.setImageDrawable(leftIcon);
        RelativeLayout.LayoutParams leftLayoutParams
                = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        mCenterView = new TextView(context);
        mCenterView.setText(centerTitle);
        RelativeLayout.LayoutParams centerLayoutParams
                = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT);
        centerLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        mRightView = new TextView(context);
        mRightView.setText(rightTitle);
        RelativeLayout.LayoutParams rightLayoutParams
                = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        addView(mLeftIconImage, leftLayoutParams);
        addView(mCenterView , centerLayoutParams);
        addView(mRightView , rightLayoutParams);
    }


    //当viewgroup 的大小为wrap_content时，要对view进行遍历，获取所有的view的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec)
        );
    }

    private int measureHeight(int heightMeasureSpec) {
        return 200;
    }

    private int measureWidth(int widthMeasureSpec) {
        int rootWidth = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            rootWidth = size;
        }else {
            rootWidth = 400;
            if (mode == MeasureSpec.AT_MOST) {
                rootWidth = Math.min(rootWidth , size);
            }
        }

        int childCount = getChildCount();

        return rootWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

}
