package com.fenfei.daggerdemo.business.user.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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
    }

    private void initView() {
        mContent = findViewById(R.id.viewmodel_content);
        mButton = findViewById(R.id.click_get);

        mButton.setOnClickListener(view -> {
            startActivity(new Intent(UserActivity.this , BookActivity.class));
        });
    }

}
