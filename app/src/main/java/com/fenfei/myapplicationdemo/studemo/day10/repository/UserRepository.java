package com.fenfei.myapplicationdemo.studemo.day10.repository;

import android.arch.lifecycle.LiveData;

import com.fenfei.myapplicationdemo.databases.UserModelDao;
import com.fenfei.myapplicationdemo.studemo.day10.models.User;
import com.fenfei.myapplicationdemo.studemo.day10.restapi.WebService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by shefenfei on 2018/1/19.
 */

public class UserRepository {

    WebService mWebService;
    UserModelDao mUserModelDao;
    ExecutorService mExecutorService;

    @Inject
    public UserRepository(WebService webService, UserModelDao userModelDao , ExecutorService executor) {
        this.mWebService = webService;
        this.mUserModelDao = userModelDao;
        this.mExecutorService = executor;
    }

    public LiveData<User> getUser(String uid) {
        refreshUser(uid);
        return mUserModelDao.loadUsers(uid);
    }


    private void refreshUser(final String userId) {
        mExecutorService.execute(() -> {
            User user = mUserModelDao.hasUser(userId);
            if (null != user) {
                try {
                    Response<User> response = mWebService.getUser(userId).execute();
                    mUserModelDao.saveUser(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
