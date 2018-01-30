package com.fenfei.camera.kotlins

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.fenfei.camera.R

/**
 * 测试Kotiln语法下的listview
 */
class ListViewActivity : AppCompatActivity() {

    var resId: Int = R.layout.item_kt_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        var lists = arrayListOf("a", "b", "a", "b", "a", "b")
        var user = User("shefenfei", "")
        println(user.toString())

        var listview = findViewById(R.id.listview) as ListView
        var adapter = MyAdapter(this, resId, lists)
        listview.adapter = adapter

        listview.setOnItemClickListener { adapterView, view, position, listener -> showSomething(lists[position]) }
//        listview.setOnClickListener { showSomething(lists[0]) }
//        listview.setOnClickListener { view -> showSomething(lists[0]) }

        testLambda()

    }

    private fun testLambda() {
//        val datas = listOf(User("" ,"shefenfei" , 1))
    }


    var callback: (() -> Unit)? = {
        Log.e("TAG" , ".........")
    }

    var callback1: ((str: String) -> Unit)? = {
        Log.e("TAG", "")
    }


    //show
    fun showSomething(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        callback?.invoke()
        callback1?.invoke("aaa")
    }

    class MyAdapter(context: Context?, resource: Int, objects: List<String>) : ArrayAdapter<String>(context, resource, objects) {

        var datasets: List<String> = emptyList<String>()
        var res: Int = 0
        var c: Context? = null

        init {
            c = context
            datasets = objects
            res = resource
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = LayoutInflater.from(c).inflate(res, parent, false)
            var tv_name = view.findViewById(R.id.item_name) as TextView
            tv_name.text = datasets[position]
            return view
        }

        override fun getCount(): Int {
            return datasets.size
        }
    }
}
