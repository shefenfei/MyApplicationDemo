package com.fenfei.daggerdemo.repos;

import android.annotation.TargetApi;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fenfei.daggerdemo.api.DaggerApiService;
import com.fenfei.daggerdemo.api.NetworkBoundResource;
import com.fenfei.daggerdemo.api.Resource;
import com.fenfei.daggerdemo.business.user.beans.User;
import com.fenfei.daggerdemo.database.AppDataBase;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shefenfei on 2018/1/31.
 * 用户模块的数据来源---可以来自数据库 / 服务器
 */
@Singleton
public class UserRepository {

    private DaggerApiService mDaggerApiService;
    private AppDataBase mAppDataBase;

    @Inject
    public UserRepository(DaggerApiService daggerApiService, AppDataBase appDataBase) {
        mDaggerApiService = daggerApiService;
        mAppDataBase = appDataBase;
    }

    @TargetApi(24)
    public LiveData<List<User>> getUserList() {
        String TAG = "getUserList";

        if (Objects.equals("" , "")) {

        }

        MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
        LiveData<List<User>> users =  mAppDataBase.getUserDAO().userList();

        if (users != null) {
            return users;
        } else {
            mDaggerApiService.getAllUsers().enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage() );
                }
            });
        }
        return mutableLiveData;
    }


    public void getUserInfo(String uid) {
        String TAG = "UserRepository";
        mDaggerApiService.getUserInfo(uid).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(TAG, "onResponse: " + response.body() );
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }


    public void userLogin() {
        mDaggerApiService.userLogin().enqueue(new Callback<Resource<User>>() {
            @Override
            public void onResponse(Call<Resource<User>> call, Response<Resource<User>> response) {
                User data = response.body().data;
            }

            @Override
            public void onFailure(Call<Resource<User>> call, Throwable t) {

            }
        });
    }


    public void testNetBoundResource() {
        new NetworkBoundResource<User , User>(){

            @Override
            protected void saveCallResult(@NonNull User item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<User>> createCall() {
                return null;
            }

        }.getAsLiveData();
    }
}
