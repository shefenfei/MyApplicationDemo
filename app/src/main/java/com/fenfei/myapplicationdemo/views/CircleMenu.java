package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shefenfei on 2017/3/21.
 * 圆形菜单
 */
public class CircleMenu extends ViewGroup {

    private String TAG = "CircleMenu";

    private int degree; // 每个item所占的角度

    public CircleMenu(Context context) {
        this(context, null);
    }

    public CircleMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO: 2017/3/21 处理item位置
        int childCount = getChildCount();
        if (childCount != 0) {
            degree = 360 / getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                int y = (int) Math.abs(Math.sin(degree * (i + 1)) * 100 );
                int x = (int) Math.abs(Math.cos(degree * (i + 1)) * 100 );
                Log.e(TAG, "onLayout: " + degree * (i + 1) + " ..." + y + "...." +x);
                view.layout(0, 0, x, y);
            }
        }
    }
}
