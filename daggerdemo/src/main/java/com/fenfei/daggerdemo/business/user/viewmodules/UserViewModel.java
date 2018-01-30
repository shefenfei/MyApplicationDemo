package com.fenfei.daggerdemo.business.user.viewmodules;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fenfei.daggerdemo.api.DaggerApiService;

/**
 * Created by shefenfei on 2018/1/25.
 */

public class UserViewModel extends ViewModel {

    private MutableLiveData<String> mData ;
    private DaggerApiService mDaggerApiService;


    public MutableLiveData<String> getData() {
        mData = mData == null ? new MutableLiveData<String>() : mData;
        /**

         mDaggerApiService.getUserInfo("uid").enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
        mData.setValue(response.body());
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
        mData.setValue(t.getMessage());
        }
        });
         */
        return mData;
    }

}
