package com.fenfei.myapplicationdemo.studemo.day11.di.modules;

import com.fenfei.myapplicationdemo.studemo.day10.restapi.WebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by shefenfei on 2018/1/23.
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    WebService providerWebService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }
}
