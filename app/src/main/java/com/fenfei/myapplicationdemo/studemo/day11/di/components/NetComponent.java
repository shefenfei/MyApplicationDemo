package com.fenfei.myapplicationdemo.studemo.day11.di.components;

import com.fenfei.myapplicationdemo.studemo.day11.di.modules.AppModule;
import com.fenfei.myapplicationdemo.studemo.day11.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shefenfei on 2018/1/23.
 */
@Singleton
@Component(modules = {AppModule.class , NetModule.class})
public interface NetComponent {

//    Retrofit retrofit();

//    OkHttpClient okHttpClicnt();
}
