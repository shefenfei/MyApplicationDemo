package com.fenfei.camera.kotlins.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat
import java.util.*

/**
 * Created by shefenfei on 2017/5/23.
 */
class MyFragment : Fragment {

    constructor()

    //拓展函数
    fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(activity, message, duration).show()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container?.setOnClickListener { toast("....") }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        view?.findViewById(0)
        super.onViewCreated(view, savedInstanceState)

        DateFormat.getInstance().format(Date())
    }

    //拓展字段
    public var TextView.text: CharSequence
    get() = getText()
    set(value) = setText(value)

}
