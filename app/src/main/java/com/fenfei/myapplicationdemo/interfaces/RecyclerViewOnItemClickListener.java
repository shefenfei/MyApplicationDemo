package com.fenfei.myapplicationdemo.interfaces;

import android.view.View;

/**
 * Created by shefenfei on 2017/6/7.
 */
//接口回调设置点击事件
public interface RecyclerViewOnItemClickListener {

    //点击事件
    void onItemClickListener(View view, int position);

    //长按事件
    boolean onItemLongClickListener(View view, int position);
}
