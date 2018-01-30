package com.fenfei.camera.kotlins

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

/**
 * Created by shefenfei on 2017/5/22.
 */
class MyView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
        TextView(context, attrs, defStyleAttr), View.OnClickListener {

    var paint: Paint? = null;

    init {
        paint = Paint()
        paint?.color = Color.RED
        paint?.isAntiAlias = true
    }


    override fun onClick(view: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            var action = event.actionMasked;
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    var rawX = event.rawX;
                    setOnClickListener(this)
                    return true
                }

            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(0f, 0f, 100f, 100f, paint)
    }


    class MyView : View {
        constructor(context: Context?) : this(context, null)
        constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    }
}