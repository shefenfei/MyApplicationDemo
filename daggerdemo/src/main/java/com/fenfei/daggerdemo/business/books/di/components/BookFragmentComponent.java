package com.fenfei.daggerdemo.business.books.di.components;

import com.fenfei.daggerdemo.business.books.ui.BookFragment;

import dagger.android.AndroidInjector;

/**
 * Created by shefenfei on 2018/1/30.
 */
//@Subcomponent
public interface BookFragmentComponent extends AndroidInjector<BookFragment> {

//    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BookFragment> {

    }

}
