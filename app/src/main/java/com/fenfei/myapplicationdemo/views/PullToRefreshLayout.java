package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;

/**
 * @author by shefenfei on 2017/3/10.
 * @desc 下拉刷新卡拉加载更多的布局
 */
// TODO: 2017/3/15  待完善下拉刷新
public class PullToRefreshLayout extends LinearLayout {

    private String TAG = "PullToRefreshLayout";

    private LayoutInflater mLayoutInflater;
    private ViewConfiguration mConfiguration;
    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private OnRefreshListener mListener;

    private AbsListView mAbsListView_;
    private View mHeaderView;
    private View mFooterView;

    private TextView mTipView;
    private ProgressBar mProgressBar;

    private int mHeaderViewHeight;
    private int mTouchSlop ; //最小的滑动条件

    private LinearLayout.LayoutParams mHeaderParams;
    //正常
    public static int PULL_TO_NORMAL = 0;
    //下拉开始
    public static int PULL_TO_START = 1;
    //刷新中
    public static int PULL_TO_REFRESHING = 2;
    //释放下拉
    public static int PULL_TO_RELEASE = 3;
    //取消下拉
    public static int PULL_TO_CANCEL = 4;

    //当前状态
    private int mCurrState = PULL_TO_NORMAL;

    private boolean isLayout = false;

    private float mDownY;
    private float mCurrY;

    private int msg_what = 0;

    //下拉刷新的临界距离
    private float PULL_TO_RELEASE_LENGTH = 200.0F;
    //可以被拉动的最大距离
    private float PULL_TO_RELEASE_MAX_LENGTH = 300.0F;

    private float mMoveLength; //下拉托动的距离

    public PullToRefreshLayout(Context context) {
        this(context, null);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mConfiguration = ViewConfiguration.get(context);
        mTouchSlop = mConfiguration.getScaledTouchSlop();
        mScroller = new OverScroller(context);

        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.pulltotefresh_layout , this , true);
        initViews(view);
    }

    private void initViews(View view) {
        mHeaderView = view.findViewById(R.id.top_header);
        mTipView = (TextView)view.findViewById(R.id.message_view);
        mAbsListView_ = (AbsListView) view.findViewById(R.id.listview);

        mHeaderParams = (LayoutParams) mHeaderView.getLayoutParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            mHeaderViewHeight = mHeaderView.getMeasuredHeight();
            scrollTo(0 , mHeaderViewHeight);
            Log.e(TAG, "onMeasure: " + mHeaderViewHeight);
        }
    }

    /**
     * 拦截下拉事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrY = ev.getY();
                if ((mCurrY - mDownY) > mTouchSlop
                        && mAbsListView_.getFirstVisiblePosition() == 0
                        && mAbsListView_.getChildAt(0).getTop() >= 0) {
                    Log.e(TAG, "onInterceptTouchEvent: " + "成功拦截" );
                    //拦截条件
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: " + "down" );
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: " + "move" );
                mMoveLength = (event.getY() - mDownY) - mHeaderViewHeight;
                scrollTo(0 , -(int) mMoveLength);
                mTipView.setText("下拉刷新...");
                if (Math.abs(mMoveLength) > PULL_TO_RELEASE_LENGTH) {
                    //释放可以刷新
                    mTipView.setText("释放刷新...");
                    scrollTo(0 , -(int) PULL_TO_RELEASE_LENGTH);
                }
                return true;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: " + "up ...... ");
                if (Math.abs(mMoveLength) < PULL_TO_RELEASE_LENGTH) { //取消刷新
                    scrollTo(0 ,  mHeaderViewHeight);
                }else {
                    //刷新中
                    mTipView.setText("刷新中...");
                    mHandler.sendEmptyMessageDelayed(msg_what , 1000);
                }
                //重置数据
                break;
        }
        return super.onTouchEvent(event);
    }

    private void resetStatus() {
        mDownY = 0;
        mCurrY = 0;
        mMoveLength = 0;
        mTipView.setText("下拉刷新...");
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mListener.refresh();
            mScroller.startScroll(0, mScroller.getFinalY(), 0, 0, 500);
            resetStatus();
            invalidate();
        }
    };


    public void setAdapter(BaseAdapter adapter) {
        if (adapter != null)
            mAbsListView_.setAdapter(adapter);
    }


    public interface OnRefreshListener {

        void refresh();

        void onLoadMore();
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX() , mScroller.getCurrY());
            invalidate();
        }
        super.computeScroll();
    }
}
