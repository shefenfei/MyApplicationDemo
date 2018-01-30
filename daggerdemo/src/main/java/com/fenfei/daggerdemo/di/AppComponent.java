package com.fenfei.daggerdemo.di;

import com.fenfei.daggerdemo.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by shefenfei on 2018/1/23.
 * the parent component 父级的组件
 */
@Singleton
@Component(modules = {
            AndroidSupportInjectionModule.class ,
            AppModules.class ,
            BuildersModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);

        AppComponent build();
    }

    void inject(App app);
}
