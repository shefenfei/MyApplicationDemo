package com.fenfei.myapplicationdemo.studemo.day11.di.components;

import com.fenfei.myapplicationdemo.studemo.day11.di.scopes.UserScope;

import dagger.Component;

/**
 * Created by shefenfei on 2018/1/23.
 * 主要的App 级别的component
 */
@UserScope
@Component()
public interface Day11Component {

//    void inject(Day11Activity activity);

}
