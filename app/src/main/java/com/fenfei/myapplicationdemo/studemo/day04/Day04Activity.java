package com.fenfei.myapplicationdemo.studemo.day04;

import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.viewmodels.MyViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Day04Activity extends AppCompatActivity {


    String TAG = "Day04Activity";

    @TargetApi(24)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day04);

        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getUsers().observe(this, users -> {
            //Update UI
            if (users != null) {
                users.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                user -> { //success
                                    Log.e(TAG, "onCreate: " + user.toString());
                                }, error -> { //error
                                    Log.e(TAG, "onError: " + error.getMessage());
                                }, () -> { //complete
                                    Log.e(TAG, "请求complete");
                                });
            }

        });
    }
}
