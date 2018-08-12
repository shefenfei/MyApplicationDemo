package com.fenfei.camera.kotlins.kotlins_day01

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.fenfei.camera.R
import com.fenfei.camera.kotlins.User
import kotlinx.android.synthetic.main.activity_day01.*
import java.io.File

/**
 * Kotlin 语法学习01
 *
 * 类布局 通常，一个类的内容按以下顺序排列：
1,属性声明与初始化块
2,次构造函数
3,方法声明
4,伴生对象
 */
class Day01Activity : AppCompatActivity() {

    private val tag: String = "Day01Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day01)
//        Log.e(tag ,sum(1,1).toString())

        //这个 is 或 !is
        if (hahhahaha_text !is Button) {
            hahhahaha_text.text = getString(R.string.hello_blank_fragment)
        } else {

        }

//        testStrTemplate()

//        testLooper()

//        testWhen(hahhahaha_text)

//        testRange(-1)

//        testFunction()

//        testMap()
//        testLazy()
        val v = maxOf2(1, 2)
//        Log.e(tag , v.toString())

//        testSingle()

//        testFirstEle()

//        testIfNotNull(null)
//        testTryCatch()

        testIfElse(-1)

        Log.e(tag , userNumber.toString())

//        trim()

        testLooper()
    }


    fun sum(x: Int, y: Int): Int {
        return x + y;
    }

    fun print(x: String) {
        println("$x su")
        //局部变量可以不赋值，但是一定要给定一个类型,在用之前一定要给值
        val c: Int

    }

    //全局变量
    val a: Int = 1
    val b = 1

    fun testStrTemplate() {
        var a = 1
        val s1 = " a is $a"

        a = 2
        val s2 = "${s1.replace("is", "was")} , but now is $a"

        Log.e(tag, s2)
    }

    /**
     * 相当于java的三元表达式
     */
    fun maxOf1(x: Int, y: Int) = if (x > y) x else y

    fun maxOf2(x: Int, y: Int) = x > y ?: x

    fun maxOf(x: Int, y: Int): Int {
        val p1 = parse(x)
        val p2 = parse(y)

        if (x > y) {
            if (p1 != null && p2 != null) {
                return p1 + p2
            }
            return x
        } else {
            return y
        }
    }


    //    当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
    fun parse(x: Int): Int? {
        return x
    }

//    is 运算符检测一个表达式是否某类型的一个实例。 如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换

//    is 相当于Java中的instanceof  做类型转换

    fun testLooper() {
        // indices 指列表的索引 数组集合的索引从0开始
        val items = listOf("a", "b", "c", 1)
        for (item in items.indices) {
            Log.e(tag, item.toString())
        }

        for ((index ,value) in items.withIndex()) {
            Log.e(tag , "$index " + ".. $value")
        }

        loop@ for (i in 1..100) {
            for (j in 1..100) {
            }

        }


//        val x = testWhen(1)
    }


    // Any是Kotlin语言的 超级对象，也就是像java的Object 对象一样 , when() 语法有点像java的switch ，
    // 但是要比switch 要强大很多
    fun testWhen(x: Any) {
        when (x) {
            testLazy() -> {

            }
            1 -> "1"
            is View -> Log.e(tag, "这是表达式到2这里了")
        }
    }


    //使用区间（range） 使用 in 或 !in 运算符来检测某个数字是否在指定区间内：
    fun testRange(x: Int) {

        //in 或 !in
        if (x in 1..10) {
            Log.e(tag, "在区间内")
        } else {
            Log.e(tag, "不在区间内")
        }

        //step 从区间开始值每次 + step 并 最后的值小于右边界
        for (x1 in 1..10 step 2) {
            Log.e(tag, x1.toString())
        }
    }


    //高阶函数 it 一定要记住
    fun testFunction() {
        val list = listOf<String>("a", "b", "c")
        list.filter {
            //相当于if (.....) {}
//            item -> item.startsWith("")
//            x -> x.startsWith("")
            it.startsWith("a")
        }.map {
            // 对上次拿到的值进行转化
            it.plus("1111")
        }.forEach {
            //遍历
            Log.e(tag, it)
        }
    }

    //类的使用中不需要 new 这个关键字
//    val button = Button(Day01Activity@this)


    //    k、v 可以改成任意名字
    //    listOf , mapOf 只读的list跟map
    fun testMap() {
        val map = mapOf("a" to 1, "b" to 2)
        for ((key, value) in map) {
            Log.e(tag, "$key ... $value")
        }
        val v1 = map["a"]
        Log.e(tag, v1.toString())

//        map["a"] = 3;
//        可以通过map["key"] = value 给已有的map更新值
    }


    val p by lazy {
        val u = User("", "")
        "".plus(1)
    }


    fun testLazy() {
        Log.e(tag, p)
    }


    fun testSingle() {
        val map = mapOf("k1" to 1)
        //if null 返回一个值或表达式 也可以是一个函数 a1()
        val result = map["k2"] ?: "没有对应的值"
        Log.e(tag, result.toString())
    }

    fun a1() = 2


    fun testFirstEle() {
        val list = listOf(null);
        //如果有值，返回第一个，没有值就返回null   可以用if null 处理
        val result = list.firstOrNull() ?: "里面没有元素"
        Log.e(tag, result)
    }


    //if not null 执行代码 ?:
    fun testIfNotNull(value: String?) {
        val any = value?.let {
            val v = value.plus("新加")
            Log.e(tag, v)
        } ?: "空值"

        if (any is String) {
            Log.e(tag, any)
        }
//        Log.e(tag , value)

    }


    //“try/catch”表达式
    fun testTryCatch() {
        val result = try {
            count()
        } catch (e: Exception) {
            Log.e(tag, e.message)
        }
    }

    fun count() = File("")


    //if”表达式
    fun testIfElse(x: Int) {
        val result = if (x > 0) {
            2
        } else if (x == 0) {
            "1"
        } else {
            false
        }

        val r = when (result) {
            is Int -> "整型"
            is String -> "字符串"
            is Boolean -> "布儿"
            else -> "不知道什么"
        }

        Log.e(tag, r)
    }


    //单表达式函数与其它惯用法一起使用能简化代码，例如和 when 表达式一起使用：
    fun transform(color: String): Int = when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }


    //对一个对象实例调用多个方法 （with)
    fun testWithObject() {


        val user = User("1", "shefenfei")
        with(user) {
            //            elementList
            test("1")
        }
    }


    val userNumber = 6221_0219_9001_1134_18L


    val text = """
        for ( c in 1..9) {
            sout
        }
        """

    fun trim() {
        Log.e(tag , text)
    }



}
