package com.fenfei.daggerdemo.business.user.di.components;

import com.fenfei.daggerdemo.business.user.ui.UserActivity;

import dagger.android.AndroidInjector;

/**
 * Created by shefenfei on 2018/1/23.
 * define a child component
 */
//目标类 与 依赖类的桥接器
//@UserScope
//@Subcomponent(modules = UserModule.class)
public interface UserComponent extends AndroidInjector<UserActivity> {

//    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserActivity> {

    }
}
