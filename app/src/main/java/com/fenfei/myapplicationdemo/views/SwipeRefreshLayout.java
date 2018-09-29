package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by shefenfei on 2017/3/7.
 */
public class SwipeRefreshLayout extends LinearLayout {

    private String TAG = "SwipeRefreshLayout";

    private GestureDetector mDetector;
    private OverScroller mScroller;
    private OnRefreshListener mListener_;

    /**
     * 刷新头View
     */
    private View mHeaderView;
    private TextView mHeaderTipView;
    /**
     * 底部加载更多View
     */
    private View mFootView;

    private int mHeaderViewHeight;

    private float mInitialX, mInitialY;

    /* Drag threshold */
    private int mTouchSlop;
    private float mDownX;

    private float mDownY;
    private float mCurrX;

    private float mCurrY;
    //listview向下滑动的距离
    private int moveLength;

    private int PULL_LENGTH = 500; //最大可拖动的距离


    public SwipeRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(VERTICAL);
        mDetector = new GestureDetector(context, mListener);
        mScroller = new OverScroller(context, new DecelerateInterpolator());
        //Get system constants for touch thresholds
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initTopView(context);
    }

    private void initTopView(Context context) {
        LinearLayout topLayout = new LinearLayout(context);
        topLayout.setGravity(Gravity.CENTER);
        topLayout.setBackgroundColor(Color.parseColor("#e5e5e5"));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        topLayout.setOrientation(HORIZONTAL);
        topLayout.setLayoutParams(params);

        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mHeaderTipView = new TextView(context);
        mHeaderTipView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mHeaderTipView.setGravity(Gravity.CENTER);
        mHeaderTipView.setText("刷新中...");

        topLayout.addView(progressBar);
        topLayout.addView(mHeaderTipView);
        addView(topLayout);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mHeaderView = getChildAt(0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        this.scrollTo(0, mHeaderViewHeight);
    }


    /*
    * 对 move事件进行拦截
    */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean isIntercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialX = event.getX();
                mInitialY = event.getY();
                mDetector.onTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                final float x = event.getX();
                final float y = event.getY();
                final int yDiff = (int) Math.abs(y - mInitialY);
                if (yDiff > mTouchSlop) { //拦截move事件
                    isIntercept = true;
                }
                break;
        }

        return isIntercept;
    }


    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        //这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
        super.computeScroll();
    }


    private Handler mHandler_ = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mHeaderTipView.setText("下拉刷新...");
            smoothScrollTo(0, mHeaderViewHeight);
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public void setListener(OnRefreshListener listener) {
        mListener_ = listener;
    }

    //处理所有的事件
    private GestureDetector.SimpleOnGestureListener mListener = new GestureDetector.SimpleOnGestureListener() {

        public boolean onDown(MotionEvent e) {
            mDownX = e.getX();
            mDownY = e.getY();
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mListener_.onRefresh();
            mHeaderTipView.setText("正在刷新...");
            mHandler_.sendEmptyMessageDelayed(0, 3000);
            invalidate();
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mCurrX = e2.getX();
            mCurrY = e2.getY();
            moveLength = (int) (mCurrY - mDownY - mHeaderViewHeight);
            if (moveLength > PULL_LENGTH) {
                mHeaderTipView.setText("松开刷新...");
            }
            int disY = (int) ((distanceY - 0.5) / 2);
            scrollBy(0, disY);
            Log.e(TAG, "moveLength: " + moveLength);
            return true;
        }
    };
}
