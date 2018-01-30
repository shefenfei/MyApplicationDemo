package com.fenfei.camera.kotlins

import android.support.annotation.UiThread
import com.google.gson.Gson
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL
import java.nio.charset.Charset

/**
 * Created by shefenfei on 2017/5/23.
 */
class Student(name: String, age: Int) {

    object getInstance {

    }

//    override fun setName1(): Int = 1
}

public class User11 {
    var name: String = ""

    get() = field.toUpperCase()
    set(value) {
        field = "ha"
    }

}

class Request(val url: String) {
    public fun run() {
        var forecastJson = URL(url).readText(charset = Charset.forName("UTF-8"))

        async() {
            val result = URL(url).readText(charset = Charset.forName("UTF-8"))
            uiThread {

            }
        }
    }




    fun async1() {
        Request(url).run()
        showMsg()
    }

    @UiThread
    fun showMsg() {

    }

    //网络请求
    fun readWeather(url: String): User {
        val userJson = URL(url).readText(charset = Charset.forName("UTF-8"))
        return Gson().fromJson(userJson , User::class.java)
    }
}
