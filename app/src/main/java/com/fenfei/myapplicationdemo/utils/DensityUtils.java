package com.fenfei.myapplicationdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by shefenfei on 2016/11/11.
 */
public class DensityUtils {

    //px2dp
    public static float convertPixelsToDp(float px){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    //dp2px
    public static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    //两个都是等价的
    public static float px2dip(Context context , float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    public static float dip2px(Context context , float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(Context context , int dp) {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dp , context.getResources().getDisplayMetrics());
        return 1;
    }
}
