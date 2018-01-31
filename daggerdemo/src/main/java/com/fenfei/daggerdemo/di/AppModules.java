package com.fenfei.daggerdemo.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by shefenfei on 2018/1/23.
 * AppModule：我们在这里提供了 retrofit、okhttp、持久化数据库、SharedPrefs。
 * 其中有一个很重要的细节，我们必须将子 Component 加入到 AppModule 中，
 * 这样 Dagger 图谱才能识别
 */
@Module(includes = NetModules.class)
public abstract class AppModules {

    @Binds
    abstract Context provideContext(Application application);
}
