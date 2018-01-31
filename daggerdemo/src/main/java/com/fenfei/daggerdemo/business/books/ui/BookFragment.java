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
import com.fenfei.daggerdemo.base.BaseFragment;
import com.fenfei.daggerdemo.business.user.beans.User;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment implements LifecycleOwner {

    String TAG = "BookFragment";

    @Inject
    Gson mGson;

    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
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
        User user = new User();
        user.setUsername("shefenfei");
        user.setPassword("123456");

        String gson = mGson.toJson(user);
        Log.e(TAG, "来自fragment 注入: " + gson );
    }
}
