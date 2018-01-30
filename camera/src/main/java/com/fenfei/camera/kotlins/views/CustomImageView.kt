package com.fenfei.camera.kotlins.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.fenfei.camera.R

/**
 * Created by shefenfei on 2017/7/20.
 * #comment
 */
class CustomImageView(context: Context) : View(context) {


    val TAG: String = "CustomImageView"

    //画笔
    var mPaint = Paint()
    var mRect = Rect()

    lateinit var mTitleText: String
    var mTitleTextSize = 12.0f
    var mTitleTextColor = Color.RED
    lateinit var mImage: Bitmap
    var mImageScaleType = 0

    constructor(context: Context, attrs: AttributeSet) : this(context) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.CustomImageView) as TypedArray
        val attrCount = typedArray.indexCount
        for (index in 0..attrCount) {
            val attr = typedArray.getIndex(index)
            when (attr) {
                R.styleable.CustomImageView_titleText -> {
                    mTitleText = typedArray.getString(attr)
                }

                R.styleable.CustomImageView_titleTextSize -> {
                    mTitleTextSize = typedArray.getDimension(attr, 12.0f)
                }

                R.styleable.CustomImageView_titleTextColor -> {
                    mTitleTextColor = typedArray.getColor(attr, Color.RED)
                }

                R.styleable.CustomImageView_image -> {
                    mImage = BitmapFactory.decodeResource(resources, typedArray.getResourceId(attr, R.mipmap.ic_launcher))
                }

                R.styleable.CustomImageView_imageScaleType -> {
                    val s = typedArray.getInt(attr, 0)
                }
            }
        }
        typedArray.recycle()
        mPaint.color = mTitleTextColor
        mPaint.strokeWidth = 4f
//        mPaint.style = Paint.Style.STROKE
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = 0
        var height = 0

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)


        Log.e("width" , widthSize.toString())
        Log.e("height" , heightSize.toString())

        //对宽进行判断
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize
        } else {
            if (widthMode == MeasureSpec.AT_MOST) {
                mPaint.getTextBounds(mTitleText , 0 , mTitleText.length , mRect)
                val w = Math.min(mRect.width() , widthSize)
                width = w
            }
        }

        //对高进行判断
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize
        } else {
            if (heightMode == MeasureSpec.AT_MOST) {
                val h = Math.max(mRect.height() , heightSize)
                height = h
            }
        }

        setMeasuredDimension(width , height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(0f ,0f  ,measuredWidth.toFloat() , measuredHeight.toFloat() , mPaint)

        mPaint.color = Color.BLUE
        canvas?.drawText(mTitleText , (width / 2).toFloat(), (height / 2).toFloat(), mPaint)
    }

    fun loge(msg: String): Unit {
        Log.e(TAG, msg)
    }

}