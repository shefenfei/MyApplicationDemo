package com.fenfei.myapplicationdemo.views;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;

/**
 * Created by shefenfei on 2016/12/20.
 */

public final class VerticalViewPagerCompat {
    private VerticalViewPagerCompat() {}

//    public interface DataSetObserver extends PagerAdapter.DataSetObserver {}

    public static void setDataSetObserver(PagerAdapter adapter, DataSetObserver observer) {
//        adapter.setDataSetObserver(observer);
    }
}
