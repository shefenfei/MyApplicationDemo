package com.fenfei.myapplicationdemo.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by shefenfei on 2016/12/20.
 */

public class TestFragmentAdapter extends FragmentPagerAdapter {
    protected static final String[] CONTENT = new String[] { "This", "Is Is", "A A A", "Test", };

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(CONTENT[position]);
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }
}
