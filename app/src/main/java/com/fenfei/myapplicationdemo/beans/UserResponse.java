package com.fenfei.myapplicationdemo.beans;

import java.io.Serializable;

/**
 * Created by shefenfei on 2017/12/13.
 */

public class UserResponse implements Serializable {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
