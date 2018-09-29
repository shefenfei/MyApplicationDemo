package com.fenfei.myapplicationdemo.beans;

import android.annotation.TargetApi;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.fenfei.myapplicationdemo.utils.DateConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

/**
 * Created by shefenfei on 2017/2/19.
 */
@Entity
public class User implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private String uid;
    private String username;
    private Integer sex;
    private int age;
    private boolean isOK;
    private int TYPE = 1; //发送者

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    @ColumnInfo(name = "birthday")
    @TypeConverters(DateConverter.class)
    private Date birthday;

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    private Integer level;


    public int getLevel() {
        return level == null ? -1 : level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public User() {
    }

    public User(String uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public User(String uid, String username, int age) {
        this.uid = uid;
        this.username = username;
        this.age = age;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @TargetApi(24)
    public String print(Function<User , String> f) {
        return f.apply(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
