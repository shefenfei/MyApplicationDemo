package com.fenfei.camera.kotlins


/**
 * Created by shefenfei on 2017/5/22.
 * DTO (POJOS & POCOS) 创建beans
 */
data class User(val userId: String, val username: String) {

    private val _elementList = mutableListOf<String>()

    val elementList: List<String>
        get() = _elementList


    //函数可以设置默认值
    fun test1(number: Int = 1) {
        test1()

    }

    fun test(any: Any) {
        when(any) {
            is String -> ""
            is Int -> 1
        }
    }

    fun User.apce() {

    }
}
