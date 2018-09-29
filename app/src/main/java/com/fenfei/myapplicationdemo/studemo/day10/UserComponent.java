package com.fenfei.myapplicationdemo.studemo.day10;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shefenfei on 2018/1/19.
 */
@Component(modules = UserModule.class)
@Singleton
public interface UserComponent {

    void inject(Day10Activity activity);


}
