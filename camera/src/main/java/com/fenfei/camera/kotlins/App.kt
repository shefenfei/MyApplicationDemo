package com.fenfei.camera.kotlins

import android.app.Application

/**
 * Created by shefenfei on 2017/6/1.
 */
class App : Application() {
    companion object {
        private var instance: Application? = null

        fun instance() = instance!!
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}