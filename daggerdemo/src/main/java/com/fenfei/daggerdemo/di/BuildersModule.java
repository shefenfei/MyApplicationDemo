package com.fenfei.daggerdemo.di;

import com.fenfei.daggerdemo.business.user.di.modules.UserModule;
import com.fenfei.daggerdemo.business.user.ui.UserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * dagger.android的实现
 * author by shefenfei on 2018/1/30.
 * 核心 : 未来的子组件构建器的绑定也需要添加到这个类中。
 */
@Module
public abstract class BuildersModule {

    /*
    @Binds
    @IntoMap
    @ActivityKey(UserActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindUserActivityInjectorFactory(UserComponent.Builder builder);
    */

    @ContributesAndroidInjector(modules = UserModule.class)
    abstract UserActivity bindUserActivity();

}
