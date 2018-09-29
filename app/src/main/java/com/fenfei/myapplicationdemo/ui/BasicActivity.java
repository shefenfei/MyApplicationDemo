package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.UserListViewModel;
import com.fenfei.myapplicationdemo.utils.MyLoactionListener;

import io.reactivex.annotations.NonNull;


public class BasicActivity extends AppCompatActivity implements LifecycleOwner {

    private MyLoactionListener mLoactionListener;

    private LifecycleRegistry mLifecycleRegistry;

    UserListViewModel mListViewModel;

    String TAG = "";

    @Override
    @TargetApi(24)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        mLoactionListener = new MyLoactionListener(this, getLifecycle());

        if (2 > 1) {
            mLoactionListener.enable();
        }

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        mListViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        mListViewModel.getUserItems().observe(BasicActivity.this, users -> {
            Log.e(TAG, "onChanged: " + users);
            assert users != null;
            users.forEach(user -> {
                System.out.println(user);
            });
        });

        mListViewModel.getUserItems();

    }


    @Override
    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
