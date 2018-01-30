package com.fenfei.camera.kotlins

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.fenfei.camera.R
import kotlinx.android.synthetic.main.activity_recycler_test.*
import org.jetbrains.anko.find

class RecyclerTestActivity : AppCompatActivity() {

    lateinit var recyclerview: RecyclerView
    lateinit var mSelectedLayout: LinearLayout
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    //存放选中的状态值
    lateinit var map: Map<Int , Boolean>
    lateinit var users: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_test)

        recyclerview = users_ry_view
        mSelectedLayout = selected_layout

        initData()
        initview()
    }

    private fun initData() {
        users = mutableListOf()
        for (index in 0..25) {
            var user = User(index.toString() , "username:" + index)
            users.add(user)
        }

        map = mutableMapOf()

    }

    private fun initview() {
        mLayoutManager = LinearLayoutManager(RecyclerTestActivity@this)
        recyclerview.layoutManager = mLayoutManager
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = MyRecyclerAdapter(users)
    }


    //在Kotlin中类可以有一个主构造函数以及多个二级构造函数。 主构造函数是类头的一部分：跟在类名后面(可以有可选的参数)。
    //适配器
    class MyRecyclerAdapter(dataset: List<User>): RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

        var datas = dataset

        //渲染视图
        override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
            val user = datas.get(position)
            Log.e("user" , user.username)
            holder?.username?.text = user.username
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user_layout , parent , false)
            val viewholder = MyViewHolder(view)
            return viewholder
        }

        override fun getItemCount(): Int = datas.size

        //viewholder
        class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val username: TextView = view.find(R.id.user_name)
        }
    }


}
