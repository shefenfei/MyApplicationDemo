package com.fenfei.myapplication.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fenfei.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("demo" , "Demo starting")
    }



}
