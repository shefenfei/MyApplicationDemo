package com.fenfei.myapplicationdemo.beans;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fenfei.myapplicationdemo.databases.AppDatabase;
import com.fenfei.myapplicationdemo.databases.UserModelDao;

import java.util.List;

/**
 * Created by shefenfei on 2017/11/9.
 * <p>
 * A ViewModel must never reference a view, Lifecycle, or any class that may hold a reference to the activity context.
 */
public class UserListViewModel extends AndroidViewModel {

    String TAG = "UserListViewModel";

    private AppDatabase mAppDatabase;
    private UserModelDao mUserModelDao;

    private MutableLiveData<PagedList<UserEntity>> mUserDatas;
    private MutableLiveData<String> mData;

    private LifecycleObserver mLifecycleObserver;//

    public UserListViewModel(@NonNull Application application) {
        super(application);

        mAppDatabase = AppDatabase.getINSTANCE(this.getApplication());
        mUserModelDao = mAppDatabase.userDao();
    }

    public LiveData<List<UserEntity>> getUserItems() {
        return mUserModelDao.getAllUsers();
    }

    public void deleteUser(UserEntity user) {
        mUserModelDao.deleteUser(user);
    }

    public void addUser(UserEntity user) {
        mUserModelDao.addUser(user);
    }


    public MutableLiveData<PagedList<UserEntity>> getUserDatas() {
        return mUserDatas == null ? new MutableLiveData<>() : mUserDatas;
    }

    public MutableLiveData<String> getData() {
        return mData == null ? new MutableLiveData<>() : mData;
    }

    public LiveData<String> listAllUserIds() {
        return new MutableLiveData<>();
    }

    public LiveData<PagedList<UserEntity>> loadUserByAge() {

        DataSource.Factory<Integer, UserEntity> entityFactory = mAppDatabase.userDao().usersOlderThan();

        LiveData<PagedList<UserEntity>> usersList = new LivePagedListBuilder<>(
                entityFactory, 20).build();

        return usersList;
    }


    private final MutableLiveData<String> addressInput = new MutableLiveData<>();
    public final LiveData<String> postCode =
            Transformations.map(addressInput , (address) -> {
       return address;
    });

    public MutableLiveData<String> getAddressInput() {
        MediatorLiveData<String> mediatorLiveData = null;
        return addressInput;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e(TAG, "onCleared: ");
    }
}
