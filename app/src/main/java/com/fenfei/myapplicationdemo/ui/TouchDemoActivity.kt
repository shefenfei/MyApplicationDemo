package com.fenfei.myapplicationdemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.fenfei.myapplicationdemo.R
import com.fenfei.myapplicationdemo.views.MyDemoAdapter
import kotlinx.android.synthetic.main.activity_touch_demo.*

class TouchDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_demo)

        val list = mutableListOf<String>("a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b")
//        val list1 = mutableListOf<String>()
        val adapter = MyDemoAdapter(this, list)

        listview.adapter = adapter
        listview.emptyView = empty_view
        listview.setSelection(10)

        //listview的滑动监听 ，listview中的最重要的技巧
        listview.setOnTouchListener(View.OnTouchListener { v, event ->
            when(event) {

            }
        })


    }
}
