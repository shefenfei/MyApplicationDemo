package com.fenfei.daggerdemo.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.fenfei.daggerdemo.business.user.viewmodules.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by shefenfei on 2018/1/29.
 */
@Module
public abstract class ViewModelModules {

    //bind each ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);



    //bind 工厂生成ViewModel
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
