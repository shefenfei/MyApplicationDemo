package com.fenfei.camera.kotlins.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.Animation
import android.widget.TextView

/**
 * Created by shefenfei on 2017/6/14.
 */
class MyTextView(override val count: Int, context: Context?, attrs: AttributeSet?) : TextView(context, attrs), Foo {
    override fun f() {

    }

    lateinit var paint: Paint

    init {
        initView()
    }

    fun initView() {
        paint = Paint()
        paint.color = Color.RED
        paint.isAntiAlias = true
    }

    override fun getAnimation(): Animation {
        return super.getAnimation()
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


interface Foo {
    val count: Int

    fun f()
}


class Bar1 : Foo , Bar3() {
    override val count: Int
        get() = 1


    override fun f() {
//        super<Foo>.f()
        super<Bar3>.f()
    }
}

class Bar2 : Foo {
    override fun f() {

    }

    override val count: Int
        get() = 1
}


open class Bar3 : Impl() {
    override fun f() {

    }

}

open class Base {
    open fun f() {}
}

abstract class Impl : Base() {
    override abstract fun f()
}

//在 Kotlin 中类没有静态方法 在大多数情况下，它建议简单地使用包级函数。

class Address {
    var name: String = ""
    var age: Int = 1
}

fun copyAddress(address: Address) : Address {
    val result = Address()
    result.age = address.age
    result.name = address.name
    return result
}


val username: String = ""
