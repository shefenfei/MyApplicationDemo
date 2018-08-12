package com.fenfei.camera.kotlins.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fenfei.camera.R
import kotlinx.android.synthetic.main.activity_coustom_view.*
import org.jetbrains.anko.toast

class CoustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coustom_view)


        text_kot.setOnClickListener {
            toast("hahaha")
        }
    }
}
