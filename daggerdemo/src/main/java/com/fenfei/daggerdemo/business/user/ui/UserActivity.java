package com.fenfei.daggerdemo.business.user.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.fenfei.daggerdemo.AppConfig;
import com.fenfei.daggerdemo.BuildConfig;
import com.fenfei.daggerdemo.R;
import com.fenfei.daggerdemo.base.BaseActivity;
import com.fenfei.daggerdemo.business.books.ui.BookActivity;
import com.fenfei.daggerdemo.business.user.viewmodules.UserViewModel;

import javax.inject.Inject;

public class UserActivity extends BaseActivity {

    private String TAG = "UserActivity";

    @Inject
    ViewModelProvider.Factory mFactory;

    private UserViewModel mUserViewModel;
    private MutableLiveData<String> mData;
    private TextView mContent;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();

        mUserViewModel = ViewModelProviders.of(this , mFactory).get(UserViewModel.class);

        MutableLiveData<String> data = mUserViewModel.getData();
        data.setValue("hahahhahaha");

        data.observe(this , s -> {
            Log.e(TAG, "onCreate: 来自viewmodel 的数据 " + s );
        });

        mUserViewModel.getUserInfo("1");
        mUserViewModel.testVieModel();
        mUserViewModel.fetchUsers().observe(this , users -> {
            Log.e(TAG, "onCreate: " + users );
        });

        if (AppConfig.DEBUG) {
            Log.e(TAG, "onCreate: "  + BuildConfig.DEBUG);
        }

        RequestManager requestManager = Glide.with(this);
        DrawableTypeRequest<String> requestBuilder;
        requestBuilder = requestManager.load("");
        requestBuilder.into(new ImageView(this));

    }

    private void initView() {
        mContent = findViewById(R.id.viewmodel_content);
        mButton = findViewById(R.id.click_get);

        mButton.setOnClickListener(view -> {
            startActivity(new Intent(UserActivity.this , BookActivity.class));
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("key1" , 1);
        outState.putInt("key2" , 2);
        Log.e(TAG, "onSaveInstanceState: ");
    }
}
