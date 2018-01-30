package com.fenfei.daggerdemo.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

/**
 * Created by shefenfei on 2018/1/29.
 */
@Module
public abstract class ViewModelModules {

    //bind each ViewModel
//    @Binds
//    @IntoMap
//    @ViewModelKey(UserViewModel.class)
//    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);



    //bind 工厂生成ViewModel
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
