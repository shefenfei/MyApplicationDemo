package com.fenfei.myapplicationdemo.studemo.day10;

import android.app.Application;

import com.fenfei.myapplicationdemo.databases.AppDatabase;
import com.fenfei.myapplicationdemo.databases.UserModelDao;
import com.fenfei.myapplicationdemo.studemo.day10.models.User;
import com.fenfei.myapplicationdemo.studemo.day10.restapi.WebService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shefenfei on 2018/1/19.
 */

@Module
class UserModule {

    @Provides
    @Singleton
    User providerUser() {
        User user = new User();
        user.setUserName("shefenfei");
        user.setUserAge(28);
        return user;
    }


    @Singleton
    @Provides
    AppDatabase provideDB(Application app) {
        return AppDatabase.getINSTANCE(app.getApplicationContext());
    }


    @Singleton
    @Provides
    UserModelDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }

    @Provides
    ExecutorService providerExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    WebService providerService() {
        Retrofit.Builder builder = new Retrofit.Builder();
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        okBuilder.connectTimeout(10 , TimeUnit.SECONDS);
        builder.baseUrl("http://www.baidu.com");
        builder.client(okBuilder.build());
        return builder.build().create(WebService.class);
    }

}
