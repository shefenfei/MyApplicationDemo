package com.fenfei.myapplicationdemo.utils;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by shefenfei on 2017/11/8.
 */

public class NameViewModel extends ViewModel {

    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getCurrentName() {
        return mCurrentName == null ? new MutableLiveData<>() : mCurrentName;
    }


}
