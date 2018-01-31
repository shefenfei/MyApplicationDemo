package com.fenfei.daggerdemo.di;

import com.fenfei.daggerdemo.MainActivity;
import com.fenfei.daggerdemo.business.books.ui.BookActivity;
import com.fenfei.daggerdemo.business.user.di.modules.UserModule;
import com.fenfei.daggerdemo.business.user.ui.UserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * dagger.android的实现
 * author by shefenfei on 2018/1/30.
 */
@Module
public abstract class ActivityBuildersModule {

    /**
     * 注入activity的方式 ，注意的是@ActivityScoped 不是必须的，但是如果要跟生命周期关联则需要
     * @return
     */
    @ActivityScoped
    @ContributesAndroidInjector(modules = {UserModule.class})
    abstract UserActivity bindUserActivity();

    /**
     * 注入fragment的时候要使用以下方式
     * @return
     */
    @ActivityScoped
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract BookActivity bindBookActivity();


    @ActivityScoped
    @ContributesAndroidInjector(modules = UserModule.class)
    abstract MainActivity bindMainActivity();
}
