package com.fenfei.daggerdemo.business.user.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.fenfei.daggerdemo.R;
import com.fenfei.daggerdemo.api.DaggerApiService;
import com.fenfei.daggerdemo.business.books.ui.BookActivity;
import com.fenfei.daggerdemo.business.user.beans.User;
import com.fenfei.daggerdemo.business.user.service.UserService;
import com.fenfei.daggerdemo.business.user.viewmodules.UserViewModel;
import com.fenfei.daggerdemo.database.AppDataBase;
import com.fenfei.daggerdemo.database.UserDAO;
import com.google.gson.Gson;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private String TAG = "UserActivity";

//    @Inject
    ViewModelProvider.Factory mFactory;

//    @Inject
    UserService mUserService;
    @Inject
    Gson mGson;
    @Inject
    DaggerApiService mApiService;

    @Inject
    AppDataBase mAppDataBase;

    private UserViewModel mUserViewModel;
    private MutableLiveData<String> mData;
    private TextView mContent;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 必须在super.onCreate(savedInstanceState);方法之前调用
//        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        User user = new User();
        user.setUsername("shefenfei");
        user.setPassword("123456");

        String gson = mGson.toJson(user);
        Log.e(TAG, "onCreate: " + gson );

//        DaggerActivity
//        DaggerFragment

        mApiService.getUserInfo("uid")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.e(TAG, "onResponse: " + response.body() );
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage() );
                    }
                });


        UserDAO userDAO = mAppDataBase.getUserDAO();
        new Thread(()-> {
            userDAO.addUser(user);
        }).start();

        userDAO.userList().observe(this , users -> {
            Log.e(TAG, "onCreate:从数据库读取" + users );
        });


        mContent = findViewById(R.id.viewmodel_content);
        mButton = findViewById(R.id.click_get);

        mButton.setOnClickListener(view -> {
            startActivity(new Intent(UserActivity.this , BookActivity.class));
        });

        /*
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mData = mUserViewModel.getData();
        mData.observe(this , datas -> {
            Log.e(TAG, "onCreate: " + datas );
            mContent.setText(datas);
        });

        mUserService.testUserService();
        */
    }

}
