package com.fenfei.myapplicationdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;

public class ScrollerViewGroupActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test);

        mViewPager = new ViewPager(this);
        mViewPager.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.header_height)));
        mListView = (ListView) findViewById(R.id.list);

        mListView.addHeaderView(mViewPager);
        mListView.setAdapter(new ScrollerViewGroupActivity.ItemsAdapter(this));

        mViewPager.setOnPageChangeListener(this);
        mViewPager.setAdapter(new ScrollerViewGroupActivity.HeaderAdapter(this));


        int i = 1;
        Log.e("num", "onCreate: " + i );
        Log.e("num", "onCreate: " + ++i );
        Log.e("num", "onCreate: " + i++ );
        Log.e("num", "onCreate: " + i );
        Log.e("num", "onCreate: " + --i );
        Log.e("num", "onCreate: " + i-- );
        Log.e("num", "onCreate: " + i );



    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageSelected(int position) { }

    @Override
    public void onPageScrollStateChanged(int state) {
        //While the ViewPager is scrolling, disable the ScrollView touch intercept
        // so it cannot take over and try to vertical scroll
        boolean isScrolling = state != ViewPager.SCROLL_STATE_IDLE;
        mListView.requestDisallowInterceptTouchEvent(isScrolling);
    }

    /*
     * Simple ListAdapter that draws a series of row items
     */
    private class ItemsAdapter extends BaseAdapter {


        private LayoutInflater mInflater;

        public ItemsAdapter(Context inflater) {
            mInflater = LayoutInflater.from(inflater);
        }

        @Override
        public int getCount() {
            return 35;
        }

        @Override
        public Object getItem(int position) {
            Log.e("-------", "getItem: "+ position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            Log.e("-------", "getItem: "+ position);
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.intercept_row, parent, false);
            }

            TextView v = (TextView) convertView.findViewById(R.id.text);
            v.setText(String.format("Item Row %d", position + 1));

            return convertView;
        }
    }

    /*
     * Simple PagerAdapter that just draws a small group of colored views
     */
    private static class HeaderAdapter extends PagerAdapter {
        private static final int[] COLORS = {0xFF555500, 0xFF770077, 0xFF007777, 0xFF777777};

        private Context mContext;

        public HeaderAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView v = new TextView(mContext);
            v.setBackgroundColor(COLORS[position]);
            v.setText(String.format("Header Card %d", position + 1));
            v.setGravity(Gravity.CENTER);
            container.addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return COLORS.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
}
