package com.fenfei.myapplicationdemo.databases;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.fenfei.myapplicationdemo.beans.UserEntity;
import com.fenfei.myapplicationdemo.studemo.day10.models.User;

/**
 * Created by shefenfei on 2017/11/9.
 */
// exportSchema = false
@Database(entities = {UserEntity.class , User.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(Context context) {
        return INSTANCE == null ? Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "app_db"
        ).build() : INSTANCE;
    }


    public abstract UserModelDao userDao();


    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
