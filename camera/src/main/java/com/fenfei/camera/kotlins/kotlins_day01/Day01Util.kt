package com.fenfei.camera.kotlins.kotlins_day01

/**
 * Created by shefenfei on 2018/2/9.
 */
//在 Kotlin 中的一个类可以有一个主构造函数和一个或多个次构造函数
class Day01Util constructor(age: Int){

    //主的构造函数不能包函任何代码，所以代码的初始化要放在init {}的代码初始化块中
    init {
        if (age == 1) {
            TODO("主构造的参数可以在初始化块中使用")
        }
    }
    companion object {
        fun testFromApi() = 1
    }

    fun t1() {

    }

    open val x1: Int
        get() = 1



}