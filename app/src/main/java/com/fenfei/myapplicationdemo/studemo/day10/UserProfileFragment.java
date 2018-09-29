package com.fenfei.myapplicationdemo.studemo.day10;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.studemo.day10.viewmodel.UserProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private static final String UID_KEY = "uid";

    UserProfileViewModel mProfileViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String userId = getArguments().getString(UID_KEY);
        mProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        mProfileViewModel.init(userId);
        mProfileViewModel.getUser().observe(this , user -> {
            //update ui
            Log.e("UserProfileFragment", "onActivityCreated: 执行 " );
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile_layout, container, false);
    }

}
