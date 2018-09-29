package com.fenfei.myapplicationdemo.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by shefenfei on 2017/12/13.
 */

public class SharedViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mLiveData = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    public void select(String response) {
        mLiveData.setValue(response);
    }


    public LiveData<String> getSelected() {
        return mLiveData;
    }

}
