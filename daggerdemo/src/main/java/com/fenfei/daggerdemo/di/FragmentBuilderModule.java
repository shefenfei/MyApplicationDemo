package com.fenfei.daggerdemo.di;

import com.fenfei.daggerdemo.business.books.di.modules.BookFragmentModule;
import com.fenfei.daggerdemo.business.books.ui.BookFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by shefenfei on 2018/1/30.
 */
@Module
public abstract class FragmentBuilderModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = BookFragmentModule.class)
    abstract BookFragment bindBookFragment();
}
