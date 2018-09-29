package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.utils.DensityUtils;

import static com.fenfei.myapplicationdemo.utils.DensityUtils.convertPixelsToDp;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener {

    private TextView mTextview , mTextview2;
    private LinearLayout mRelativeLayout_;

    private String DEBUG_TAG = "MainActivity";
    private String TAG = "MainActivity";

    private GestureDetectorCompat gesture;
    private VelocityTracker mVelocityTracker_;

    private int action;
    private int index;
    private int pointerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextview = (TextView) findViewById(R.id.text_view);
        mTextview2 = (TextView) findViewById(R.id.second_view);
        mRelativeLayout_ = (LinearLayout) findViewById(R.id.activity_main);

        gesture = new GestureDetectorCompat(this ,this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Log.e("MainActivity", "onCreate: " + mTextview.getLeft() + "..." + "top..."
                    + mTextview.getTop() + "..." + mTextview.getRight() + "..." + mTextview.getBottom());

            int width = mTextview.getRight() - mTextview.getLeft();
            Log.e("MainActivity", "onCreate: " +  width +" px");
            float w = convertPixelsToDp(width);
            Log.e("MainActivity", "onCreate: " +  w +" dip");


            float height = mTextview.getBottom() - mTextview.getTop();
            float h = DensityUtils.convertPixelsToDp(height);
            Log.e("MainActivity", "onWindowFocusChanged: " + height + "....." + h);

            Log.e("MainActivity", "onCreate: " + mRelativeLayout_.getLeft() + "..." + "top..."
                    + mRelativeLayout_.getTop() + "..." + mRelativeLayout_.getRight() + "..." + mRelativeLayout_.getBottom());


            int tv2_left = mTextview2.getLeft();
            int tv2_top = mTextview2.getTop();

            Log.e("tv2", "onWindowFocusChanged: " + tv2_left + "...." + tv2_top);

        }
    }
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        action = MotionEventCompat.getActionMasked(event);
        index = MotionEventCompat.getActionIndex(event);
        pointerIndex = event.getPointerId(index);

        /*
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                if (mVelocityTracker_ == null ) {
                    mVelocityTracker_ = VelocityTracker.obtain();
                }else {
                    mVelocityTracker_.clear();
                }
                mVelocityTracker_.addMovement(event);
                Log.d(DEBUG_TAG,"Action was DOWN");
                return false;
            case (MotionEvent.ACTION_MOVE) :
                mVelocityTracker_.addMovement(event);
                mVelocityTracker_.computeCurrentVelocity(1000);
                float yVelocity = mVelocityTracker_.getYVelocity(pointer);
                Log.e(TAG, "onTouchEvent: " + yVelocity);
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                mVelocityTracker_.recycle();
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :

                return super.onTouchEvent(event);

        }
        */
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    //action_down
    @Override
    public boolean onDown(MotionEvent e) {
        Log.e(TAG, "onDown: ");
        if (mVelocityTracker_ == null) {
            mVelocityTracker_ = VelocityTracker.obtain();
        }else {
            mVelocityTracker_.clear();
        }
        mVelocityTracker_.addMovement(e);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    //action_move
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e(TAG, "onScroll: " );
        mVelocityTracker_.addMovement(e2);
        mVelocityTracker_.computeCurrentVelocity(1000);

        //最佳实践
        float yVelocity = VelocityTrackerCompat.getYVelocity(mVelocityTracker_ , pointerIndex);
//        float yVelocity = mVelocityTracker_.getYVelocity(pointerIndex);
        Log.e(TAG, "onTouchEvent: " + yVelocity);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    //action_up
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e(TAG, "onFling: " );
        return false;
    }
}
