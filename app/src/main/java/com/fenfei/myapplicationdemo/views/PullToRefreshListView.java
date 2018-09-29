package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.fenfei.myapplicationdemo.R;

/**
 * 下拉刷新listview
 * Created by shefenfei on 2016/11/14.
 */
public class PullToRefreshListView extends LinearLayout {

    private String TAG = "PullToRefreshListView";

    private View mView_;
    private ProgressBar mProgressBar_;
    private ListView mListView_;


    public PullToRefreshListView(Context context) {
        this(context, null);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //注意下面的这句话, ViewGroup 要写this , 不然无法inflate出来组件
        mView_ = LayoutInflater.from(context).inflate(R.layout.pull2refresh_listview, this);
        init(context, mView_);

        //判断移动超过这个距离才算处理滑动
        ViewConfiguration.get(this.getContext()).getScaledTouchSlop();
    }

    private void init(Context context, View view) {
        mProgressBar_ = (ProgressBar) findViewById(R.id.pull2refresh_progressbar);
        mListView_ = (ListView) findViewById(R.id.pull2refresh_listview);
    }

//    没事不要重写这个方法 , 如果不想重新定位某个组件,不要重写这个方法,会导致组件无法显示
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        getChildAt(0).layout(0 , -(int)DensityUtils.convertPixelsToDp(40) , getWidth() , 0);
//        getChildAt(1).layout(0 , 0 , getWidth() , (int) DensityUtils.convertPixelsToDp(200));
    }

    public void setAdapter(ListAdapter adapter) {
        if (adapter != null) {
            mListView_.setAdapter(adapter);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: " + super.dispatchTouchEvent(ev) );
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent: " + "action_down" );
                return true;

            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent: " + "action_move" );
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent: " + "action_up" );
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onInterceptTouchEvent: " + "action_down" );
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onInterceptTouchEvent: " + "action_move" );
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onInterceptTouchEvent: " + "action_up" );
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
