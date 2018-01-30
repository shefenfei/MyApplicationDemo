package com.fenfei.camera.kotlins

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.fenfei.camera.R
import kotlinx.android.synthetic.main.activity_custom_view.*
import org.jetbrains.anko.backgroundColor

class CustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)

//        val v = findViewById(R.id.custom_view) as CustomImageView
//        v.setOnClickListener({
//            Log.e("" , "...")
//        })

//        cv_image.setOnClickListener({
//            Log.e("..." , "哈哈哈哈")
//        })

        myview.text = "new word "

        val v = findViewById(R.id.cv_image) as? View
        v?.setOnClickListener({
            Log.e("...." , text_aaa.text.toString())
        })

        text_aaa.setOnClickListener({
            Log.e("...." , text_aaa.text.toString())
        })


        activity_custom_view.backgroundColor = Color.CYAN
    }
}
