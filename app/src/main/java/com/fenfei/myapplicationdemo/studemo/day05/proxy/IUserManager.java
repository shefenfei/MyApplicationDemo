package com.fenfei.myapplicationdemo.studemo.day05.proxy;

import com.fenfei.myapplicationdemo.beans.User;

/**
 * Created by shefenfei on 2017/12/25.
 */

public interface IUserManager {

    int addUser(User user);

    int delUser(int id);
}
