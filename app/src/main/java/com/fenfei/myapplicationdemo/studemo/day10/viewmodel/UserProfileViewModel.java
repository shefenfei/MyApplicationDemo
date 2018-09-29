package com.fenfei.myapplicationdemo.studemo.day10.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.fenfei.myapplicationdemo.studemo.day10.models.User;
import com.fenfei.myapplicationdemo.studemo.day10.repository.UserRepository;

/**
 * Created by shefenfei on 2018/1/19.
 * userprofile 的viewmodel
 */
public class UserProfileViewModel extends ViewModel {

    private String userId;
    private LiveData<User> mUser;

    UserRepository userRepo;


    public UserProfileViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String userId) {
        if (this.userId != null) {
            return;
        }
        mUser = userRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return mUser;
    }
}
