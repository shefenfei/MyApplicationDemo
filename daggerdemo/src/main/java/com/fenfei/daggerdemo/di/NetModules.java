package com.fenfei.daggerdemo.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fenfei.daggerdemo.api.DaggerApiService;
import com.fenfei.daggerdemo.database.AppDataBase;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shefenfei on 2018/1/23.
 */
@Module
public class NetModules {

    @Provides
    @Singleton
    AppDataBase providesAppDataBase(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                AppDataBase.class,
                "dagger_db")
                .build();
    }


    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    @Provides
    @Singleton
    Gson providesGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }


    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return builder.build();
    }


    @Provides
    @Singleton
    Retrofit providesRetrofit(Gson gson , OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.baidu.com")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public DaggerApiService providesDaggerApiService(Retrofit retrofit) {
        return retrofit.create(DaggerApiService.class);
    }


}
