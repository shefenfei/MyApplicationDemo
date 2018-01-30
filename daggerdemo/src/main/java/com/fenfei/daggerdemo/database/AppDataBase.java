package com.fenfei.daggerdemo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.fenfei.daggerdemo.business.user.beans.User;

/**
 * Created by shefenfei on 2018/1/30.
 */
@Database(version = 1 , exportSchema = false , entities = {User.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDAO getUserDAO();
}
