package com.fenfei.camera.kotlins

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.fenfei.camera.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_kotiln.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.io.File
import java.net.URL

class KotilnActivity : AppCompatActivity() {

    var str: String = "abc"
    var num = 1
    var s = "abcdefg"


    var x: Int? = null

    var s1 = "a is $s";

    var s2 = "${s.replace("a", "kkk")}";


    fun getToolbar(): android.support.v7.widget.Toolbar {
        return Toolbar(this);
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotiln)


        testHttp(button_req)
        button_req.setOnClickListener { toast("") }
        testFilter()

//        button_req.setOnClickListener({ view -> toast("") })

        x = 1;

        t_name.text = "hello kotiln-----" + str;
        t_name.isClickable = true;
        t_name.textSize = 20.0f;
        t_name.setOnClickListener {
            var intent = Intent(KotilnActivity@ this, ListViewActivity::class.java)
            startActivity(intent)
        }


        testLambada()

        edit_view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


        var a = 4
        when (a) {
            1 -> {
                t_name.text = "hello kotiln-----" + str;
            }

            2 -> {
                t_name.text = "hello kotiln 2-----" + str;
            }
            else -> {
                Log.e("AAA", test(a));
            }
        }


        sum(1, 2);

        var re = maxOf(1, 2);

        printProduct("abc", "aaa")

//        looperTest()
//        looperTest1()

        whileTest()

        useRanges()

        var result = describe(10L)
        println(result)

//        setSupportActionBar(getToolbar());

        testWhenSynix(listOf("a", "first o", "sce"))

        testMap(mapOf("a" to 1, "b" to 2))

        Observable.create<ObservableOnSubscribe<String>> {
            e: ObservableEmitter<ObservableOnSubscribe<String>>? ->
        }

        Observable.just("").subscribe { }
    }

    //网络请求
    private fun testHttp(textView: TextView) {
        val url = "https://api.sit.ffan.com/ffan/v4/order?FFClientVersion=0418000000&puid=FF23A88453A440408442FD4D83AD81DE"
        async() {
            val result = URL(url).readText()
            uiThread {
                textView.text = result
                Log.e("Resp", result)
            }
        }
    }

    fun test(type: Int): String {
        var result = when (type) {
            0, 1 -> "a"
            else -> "b"
        }
        return result;
    }

    //函数
    fun sum(a: Int, b: Int): Int {
        println(a)
        println(s1)
        println(s2)
        return a + b
    }

    //函数
    fun sum1(a: Int, b: Int) = a + b


    //条件表达式
    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }


    //条件表达式
    fun maxOf1(a: Int, b: Int) = if (a > b) a else b

    fun parseInt(num: String): Int? {
        if (num.equals("abc")) {
            return 1
        }
        return -1
    }


    fun printProduct(arg1: String, arg2: String) {
        var x1 = parseInt(arg1);
        var x2 = parseInt(arg2);

        if (x1 != null && x2 != null) {
            println(x1 * x2);
        } else {
            println("not a number");

        }
    }

    //类型转换
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            return obj.length
        }
        return null
    }

    //类型转换
    fun getStringLength1(obj: Any): Int? {
        if (obj !is String) return null
        return obj.length
    }

    //类型转换
    fun getStringLength2(obj: Any): Int? {
        if (obj is String && obj.length > 0)
            return obj.length

        return null
    }

    //java的 for-each
    fun looperTest() {
        var list = listOf("A", "B", "C")
        for (l in list) {
            println(l)
        }
    }

    //java的 for (int i=0;i<count;i++) {....}
    fun looperTest1() {
        var items = listOf("A", "B", "c")
        for (index in items.indices)
            println("item at $index is ${items[index]}")
    }

    //while 循环
    fun whileTest() {
        var items = listOf("a", "b", "c")
        var index = 0
        while (index < items.size) {
            println("${items[index]}")
            index++
        }
    }

    //java的switch语句
    fun describe(obj: Any): String = when (obj) {
        1 -> "One"
        "hello" -> "Gretting"
        is Long -> "Long"
        !is Long -> "not Long"
        else -> "Unknown"
    }

    //use ranges 边界
    fun useRanges() {
        var x = 10
        var y = 9
        if (x in 1..y) {
            println("fits in range")
        } else {
            println("not fit range")
        }

        val list = listOf("a", "b", "c")
        if (-1 !in 0..list.lastIndex) {
            println("-1 is out of range")
        }
        if (list.size !in list.indices) {
            println("list size is out of valid list indices range too")
        }

        //区间 [1,5]
        for (x in 1..5) {
//            println(x);
        }

        //从 [1,10] 移动3步 也就是间隔多少 step
        for (y in 1..10 step 4) {
//            println(y)
        }

        for (a in 9 downTo 0 step 3) {
            println(a)
        }

    }


    fun testFilter() {
        val datas = listOf("a", "b", "c")
        val filter = datas.filter { x -> x == "a" }
        Log.e("//////", filter.toString())
    }

    fun testWhenSynix(items: List<String>) {
        var user = User("a", "");
        //在这个区域内可直接使用类的方法
        with(user) {
            //            test1(1)

        }


        items.filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }

        if (items.isNotEmpty()) {
            for (item in items) {
                when (item) {
                    "first o" -> println("success")
                }
            }
        }
    }

    fun testMap(map: Map<String, Int>) {
        "".createName()
        for ((k, v) in map) {
            println("$k ---> $v")
        }
    }

    fun getColor(color: String): Int
            = when (color) {
        "R" -> 0
        "G" -> 1
        "B" -> 2
        else -> 1
    }

    fun arrayOfMinusOnes(size: Int): IntArray = IntArray(size).apply { fill(-1) }

    //扩展函数
    fun String.createName() {

    }

    //学习lambada表达式
    fun testLambada() {
        val datas = listOf("a", "b", "c", 1)

        Observable.fromArray(datas)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loge(it)
                    for (item in datas) {
                        if (item is Int) {
                            loge(item)
                        }
                    }
                }



        Observable.create<String> {
            emitter ->
            kotlin.run {
                emitter.onNext("来自create A")
                emitter.onNext("a")
                emitter.onNext("b")
                emitter.onNext("c")
                emitter.onNext("d")
                emitter.onNext("e")
            }
        }.subscribe {
            loge(it)
        }


//        val observer = Observer

    }

    fun loge(msg: Any) {
        Log.e("TAG", msg.toString())
    }

    //Object学习
    fun testObject(file: File, str: Int): String {
//        getColor("R")


        val result = if (str == 1) {
            "aaa"
        } else {
            "bbb"
        }
        return result

        if (file.isDirectory) {
            file?.listFiles() //如果它不为空的时候就执行方法
        }
    }

    fun testAndroid() {
        var x: Boolean = false
        var y: Boolean = true


        Observable.just("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {}

        Toast.makeText(this, "....", Toast.LENGTH_SHORT).show()
    }


}

