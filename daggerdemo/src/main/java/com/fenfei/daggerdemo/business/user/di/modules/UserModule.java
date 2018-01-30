package com.fenfei.daggerdemo.business.user.di.modules;


import com.fenfei.daggerdemo.api.DaggerApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by shefenfei on 2018/1/23.
 */
@Module()
public class UserModule {

    @Provides
    public DaggerApiService providesDaggerApiService(Retrofit retrofit) {
        return retrofit.create(DaggerApiService.class);
    }

}
