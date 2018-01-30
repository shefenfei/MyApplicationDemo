package com.fenfei.daggerdemo.business.books.ui;


import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fenfei.daggerdemo.R;
import com.fenfei.daggerdemo.database.AppDataBase;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment implements LifecycleOwner {

    String TAG = "BookFragment";

    @Inject
    AppDataBase mAppDataBase;

    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAppDataBase.getUserDAO().userList().observe(this , users -> {
            Log.e(TAG, "onActivityCreated: 来自 fragment" + users);
        });
    }
}
