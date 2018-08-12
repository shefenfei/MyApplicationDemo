package com.fenfei.camera.kotlins.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fenfei.camera.R
import com.fenfei.camera.kotlins.User
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.SingleEmitter
import kotlinx.android.synthetic.main.activity_rx_java2.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RxJava2Activity : AppCompatActivity() {

    lateinit var mUserSingle: SingleEmitter<User>
    lateinit var mMaybe: Maybe<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java2)

        val retrofit = Retrofit.Builder()
//        retrofit.addConverterFactory(Co)
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofit.baseUrl("")

        val httpclient = OkHttpClient().newBuilder()

//        httpclient.addInterceptor(MyIntecepter())

        httpclient.readTimeout(10 * 1000 , TimeUnit.MILLISECONDS)


        retrofit.client(httpclient.build())


        rx_java_button.setOnClickListener {
            val user = User("id", "shefenfei_rxjava")
            if (user.username.equals("shefenfei_rxjava1"))
                mUserSingle.onSuccess(user)
            else {
                mUserSingle.onError(Throwable("hahha"))
            }
        }

        var users = mutableListOf<User>();

        for (index in 1..10) {
            var user = User("userid" + index, "username-" + index)
            users.add(user)
        }

        Flowable.fromArray(users)
                .first(users)
                .filter { users.size == 0 }

        Single.create<User> {
            e ->
            mUserSingle = e
        }.subscribe()

        Maybe.create<User> {
            e ->
            e.onSuccess(User("id", "shefenfei_rxjava"))
        }.filter { u -> u.username.equals("") }.subscribe({
            u ->
            Log.e("TAG FORM maybe", u.username)
        }, {
            e ->
            Log.e("", e.message)
        })
    }

    fun changeName(users: MutableList<User>) {
        users.forEach { s -> s.username }
    }

}
