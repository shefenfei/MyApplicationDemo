package com.fenfei.myapplicationdemo.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fenfei.myapplicationdemo.beans.UserResponse;
import com.fenfei.myapplicationdemo.http.ServiceGenerator;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;

import io.reactivex.Flowable;

/**
 * Created by shefenfei on 2017/12/13.
 */

public class MyViewModel extends AndroidViewModel {

    String TAG = "MyViewModel";

    private MutableLiveData<Flowable<UserResponse>> users;

    private FileDownloadService apiService;

    public MyViewModel(@NonNull Application application) {
        super(application);
        String baseUrl = "http://192.168.0.105:8081/";
        apiService = ServiceGenerator.generate(this.getApplication())
                .setEndpoint(baseUrl)
                .getApiService(FileDownloadService.class);
    }

    public LiveData<Flowable<UserResponse>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        users.setValue(apiService.getUserInfo("1"));
    }


    public void loadUserByAge(int age) {

    }


    @Override
    protected void onCleared() {
//        super.onCleared();
        Log.e(TAG, "onCleared: ");
    }
}
