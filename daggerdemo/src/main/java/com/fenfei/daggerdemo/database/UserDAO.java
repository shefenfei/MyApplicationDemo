package com.fenfei.daggerdemo.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.fenfei.daggerdemo.business.user.beans.User;

import java.util.List;

/**
 * Created by shefenfei on 2018/1/30.
 */
@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);


    @Query("select * from user")
    LiveData<List<User>> userList();
}
