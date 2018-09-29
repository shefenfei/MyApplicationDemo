package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fenfei.myapplicationdemo.R;

/**
 * Created by shefenfei on 2017/2/15.
 */

public class ScrollAnimationList extends FrameLayout {

    private View mRootView;
    private FrameLayout mImgLayout;
    private LinearLayout mContainerLayout;

    public ScrollAnimationList(Context context) {
        this(context , null);
    }

    public ScrollAnimationList(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public ScrollAnimationList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRootView = LayoutInflater.from(context).inflate(R.layout.scroll_animate_card , this , true);
        mImgLayout = (FrameLayout) mRootView.findViewById(R.id.bg_img_layout);
        mContainerLayout = (LinearLayout) findViewById(R.id.container_layout);
    }


    public FrameLayout getImgLayout() {
        return mImgLayout;
    }
    
}
