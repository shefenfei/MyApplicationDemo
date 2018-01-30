package com.fenfei.daggerdemo.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fenfei.daggerdemo.App;
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
 * AppModule：我们在这里提供了 retrofit、okhttp、持久化数据库、SharedPrefs。
 * 其中有一个很重要的细节，我们必须将子 Component 加入到 AppModule 中，
 * 这样 Dagger 图谱才能识别
 */
//@Module(subcomponents = {UserComponent.class , BookFragmentComponent.class})
@Module()
public class AppModules {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    @Provides
    @Singleton
    AppDataBase providesAppDataBase(App app) {
        return Room.databaseBuilder(app.getApplicationContext() , AppDataBase.class , "dagger_db").build();
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
}
