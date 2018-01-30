package com.fenfei.camera.kotlins.views

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.LinearLayout
import android.widget.OverScroller

/**
 * Created by shefenfei on 2017/6/14.
 * 使用kotlin进行自定义控件
 */
class MyScrollerLayout : LinearLayout {

    val scroller: OverScroller
    val viewConfig: ViewConfiguration

    var mSlopTouch: Int = 0
    var mLastDownX: Float = 0f
    var mLastDownY: Float = 0f

    var mCurrentDownX: Float = 0f
    var mCurrentDownY: Float = 0f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        viewConfig = ViewConfiguration.get(context)
        scroller = OverScroller(context)
        orientation = VERTICAL
        mSlopTouch = viewConfig.scaledTouchSlop
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val action = MotionEventCompat.getActionMasked(ev)

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mCurrentDownX = ev?.rawX as Float
                mCurrentDownY = ev?.rawY as Float
                mLastDownX = mCurrentDownX
            }

            MotionEvent.ACTION_MOVE -> {
                Log.e("onInterceptTouchEvent", "ACTION_MOVE")
                mCurrentDownX = ev?.rawX as Float
                Log.e("TAG", (mCurrentDownX - mLastDownX).toString())
                val result = (mCurrentDownX - mLastDownX) > mSlopTouch
                return result
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = MotionEventCompat.getActionMasked(event)
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("onTouchEvent", "down.....")
            }

            MotionEvent.ACTION_MOVE -> {
                scroller.startScroll(scrollX , scrollY , -scrollX , -scrollY)
                Log.e("onTouchEvent", "move.....")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX , scroller.currY)
            invalidate()
        }
    }

}