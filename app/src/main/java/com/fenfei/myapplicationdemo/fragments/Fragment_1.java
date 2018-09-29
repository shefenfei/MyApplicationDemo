package com.fenfei.myapplicationdemo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fenfei.myapplicationdemo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_1 extends Fragment {

    private String[] titles = {"Exchange" ,"Activity"};
    private ViewPager mViewPager_;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragments_ = new ArrayList<>();

    public Fragment_1() {
        // Required empty public constructor
    }

    public static Fragment_1 newInstance() {
        return new Fragment_1();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager_ = (ViewPager) view.findViewById(R.id.vp_body);

        mTabLayout.addTab(mTabLayout.newTab().setText(titles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[1]));

        TabFragment tabFragment = TabFragment.newInstance();
        AFragment aFragment = AFragment.newInstance();
        mFragments_.add(tabFragment);
        mFragments_.add(aFragment);

        TabFragmentPagerAdapter adapter =  new TabFragmentPagerAdapter(this.getChildFragmentManager() , mFragments_);
        mViewPager_.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager_);
//        mTabLayout.setTabsFromPagerAdapter(adapter);
    }


    public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> mFragmentsList;

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
            super(fm);
            mFragmentsList = fragmentsList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentsList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
