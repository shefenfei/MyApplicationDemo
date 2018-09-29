package com.fenfei.myapplicationdemo.studemo.day10;

import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.studemo.day10.models.User;
import com.fenfei.myapplicationdemo.studemo.day10.viewmodel.UserProfileViewModel;

import javax.inject.Inject;

/**
 * Guide to App Architecture
 */
public class Day10Activity extends AppCompatActivity {

    private String TAG = "Day10Activity";

    @Inject
    User mUser;

    @Inject
    User mUser2;

    UserComponent mUserComponent;


    UserProfileViewModel mUserProfileViewModel;

    @Override
    @TargetApi(24)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day10);


        mUserComponent = DaggerUserComponent.builder().build();
        mUserComponent.inject(this);


        Log.e(TAG, "onCreate: " + mUser + "...." + mUser2);

        mUserProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        mUserProfileViewModel.getUser().observe(this , user -> {
            Log.e(TAG, "onCreate: " + user );
        });


//        UserProfileFragment profileFragment = new UserProfileFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("uid" , "1");
//        profileFragment.setArguments(bundle);
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container ,profileFragment).commit();

    }

}
