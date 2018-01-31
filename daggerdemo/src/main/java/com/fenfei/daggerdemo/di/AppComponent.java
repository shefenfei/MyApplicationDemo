package com.fenfei.daggerdemo.di;

import android.app.Application;

import com.fenfei.daggerdemo.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by shefenfei on 2018/1/23.
 * the parent component 父级的组件
 */
@Singleton
@Component(modules = {
        AppModules.class,
        ActivityBuildersModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
