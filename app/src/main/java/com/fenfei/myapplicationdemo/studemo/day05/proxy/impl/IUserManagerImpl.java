package com.fenfei.myapplicationdemo.studemo.day05.proxy.impl;

import com.fenfei.myapplicationdemo.beans.User;
import com.fenfei.myapplicationdemo.studemo.day05.proxy.IUserManager;

/**
 * Created by shefenfei on 2017/12/25.
 */

public class IUserManagerImpl implements IUserManager {

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int delUser(int id) {
        return 0;
    }
}
