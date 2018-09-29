package com.fenfei.myapplicationdemo.studemo.day11.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shefenfei on 2018/1/23.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providerApplication() {
        return mApplication;
    }
}
