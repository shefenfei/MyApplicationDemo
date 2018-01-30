package com.fenfei.camera.kotlins.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView

/**
 * Created by shefenfei on 2017/6/14.
 */
class MyTextView : TextView {

    lateinit var paint: Paint

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)

    init {
        initView()
    }

    fun initView() {
        paint = Paint()
        paint.color = Color.RED
        paint.isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText("hahahhaha", 100f, 100f, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return super.onTouchEvent(event)
        return true
    }

}