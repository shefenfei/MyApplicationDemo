package com.fenfei.myapplicationdemo.studemo.day03;

import android.annotation.TargetApi;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.User;
import com.fenfei.myapplicationdemo.beans.UserEntity;
import com.fenfei.myapplicationdemo.beans.UserListViewModel;
import com.fenfei.myapplicationdemo.databases.UserModelDao;
import com.fenfei.myapplicationdemo.studemo.day08.Day08Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 测试 Room框架
 */
public class Day03Activity extends AppCompatActivity {


    String TAG = "Day03Activity";

    UserModelDao modelDao;
    LiveData<List<UserEntity>> allUsers;
    UserListViewModel model;

    @Override
    @TargetApi(24)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day03);

//        You usually request a ViewModel the first time the system calls an activity object's onCreate() method.

        model = ViewModelProviders.of(Day03Activity.this).get(UserListViewModel.class);
        model.getUserItems().observe(this , userEntities -> {
            Optional.ofNullable(userEntities).ifPresent(userEntities1 -> {
                userEntities1.forEach(userEntity -> {
                    Log.e(TAG, "onCreate: " + userEntity );
                });
            });
        });

        Observer<List<UserEntity>> observer = new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntity) {
                Log.e(TAG, "onChanged:" + userEntity.toString());
            }
        };

        Button addBtn = (Button) findViewById(R.id.add_user);
        Button getBtn = (Button) findViewById(R.id.get_users);

        addBtn.setOnClickListener(view -> {
            new Thread(() -> {
                UserEntity user = new UserEntity();
                user.setUsername("shefenfei");
                user.setPassword("123456");
                user.setAddress("上海");
                model.addUser(user);
            }).start();
        });

        getBtn.setOnClickListener(view -> {
            jump();
//            testObserver(observer);
//            testLoad();
        });
    }

    @TargetApi(24)
    private void testLoad() {
        model.loadUserByAge().observe(this, userEntities -> {
            userEntities.parallelStream().forEach(userEntity -> {
                Log.e(TAG, "testLoad: " + userEntity.toString() );
            });
        });
    }



    private void jump() {
        Intent intent = new Intent(Day03Activity.this , Day08Activity.class);
        startActivity(intent);
    }

    private void testObserver(Observer<List<UserEntity>> observer) {
        allUsers = model.getUserItems();
        Log.e(TAG, "onClick: " + allUsers);
        allUsers.observe(Day03Activity.this, observer);
    }


    @TargetApi(24)
    private void testJava8() {
        List<User> users = new ArrayList<>();
//        users.stream().
        Optional<User> optional = null;
        Consumer<User> consumer = user -> {
            // TODO: 2017/12/7
        };
    }

    @TargetApi(24)
    public Optional<User> getUser() {
//        Optional.ofNullable()
        return Optional.of(new User());
    }
}
