package com.fenfei.camera.kotlins

/**
 * Created by shefenfei on 2017/5/23.
 */
class Person(var name: String , var age: Int) {
    //由于主的构造函数不能添加其它的代码，要做初始化的时候，要借助init {}
    init {
        name = "shefenfei"
        age = 27
    }

    //二级的构造函数
    constructor(name: String , age: Int , address: String): this(name , age) {

    }


    public fun test() {

    }
}
