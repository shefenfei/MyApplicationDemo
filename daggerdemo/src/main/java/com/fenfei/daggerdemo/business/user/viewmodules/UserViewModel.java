package com.fenfei.daggerdemo.business.user.viewmodules;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.fenfei.daggerdemo.business.user.beans.User;
import com.fenfei.daggerdemo.repos.UserRepository;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by shefenfei on 2018/1/25.
 */
public class UserViewModel extends ViewModel {

    private String TAG = "UserViewModel";

    private MutableLiveData<String> mData ;
    private UserRepository mUserRepository;

    @Inject
    Gson mGson;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    public MutableLiveData<String> getData() {
        mData = mData == null ? new MutableLiveData<String>() : mData;
        return mData;
    }


    /**
     * 获取用户的详情
     * @param uid
     */
    public void getUserInfo(String uid) {
        mUserRepository.getUserInfo(uid);
    }

    /**
     * 获取用户列表
     * @return
     */
    public LiveData<List<User>> fetchUsers() {
        return mUserRepository.getUserList();
    }


    public void testVieModel() {
        User user = new User();
        user.setUsername("shefenfei");
        user.setPassword("123456");

        String gson = mGson.toJson(user);
        Log.e(TAG, "onCreate: " + gson );
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e(TAG, "onCleared: ");
    }
}
