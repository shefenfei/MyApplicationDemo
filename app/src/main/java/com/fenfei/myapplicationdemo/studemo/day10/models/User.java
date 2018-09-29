package com.fenfei.myapplicationdemo.studemo.day10.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by shefenfei on 2018/1/19.
 */

@Entity
public class User {

    @PrimaryKey
    private int userId;
    private String userName;
    private int userAge;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }


}
