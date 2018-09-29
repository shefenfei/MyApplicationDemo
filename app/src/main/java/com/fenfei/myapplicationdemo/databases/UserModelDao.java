package com.fenfei.myapplicationdemo.databases;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.fenfei.myapplicationdemo.beans.UserEntity;
import com.fenfei.myapplicationdemo.studemo.day10.models.User;
import com.fenfei.myapplicationdemo.utils.DateConverter;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by shefenfei on 2017/11/9.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface UserModelDao {

    @Query("select * from userentity")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("select * from userentity where uid = :uid")
    UserEntity findUserById(String uid);

    @Insert(onConflict = REPLACE)
    void addUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);

    @Query("select * from userentity")
    DataSource.Factory<Integer , UserEntity> usersOlderThan();

    @Insert(onConflict = REPLACE)
    void saveUser(User user);

    @Query("select * from user where userId = :userId")
    LiveData<User> loadUsers(String userId);

    @Query("select * from user where userId = :userId")
    User hasUser(String userId);
}

