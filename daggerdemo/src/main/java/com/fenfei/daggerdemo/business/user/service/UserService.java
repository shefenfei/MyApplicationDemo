package com.fenfei.daggerdemo.business.user.service;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by shefenfei on 2018/1/23.
 */

public class UserService {

    @Inject
    public UserService() {
    }


    public void testUserService() {
        Log.e("UserService", "testUserService: " );
    }

}
