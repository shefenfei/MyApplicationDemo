package com.fenfei.myapplicationdemo.studemo.day11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.studemo.day10.models.User;
import com.fenfei.myapplicationdemo.studemo.day10.restapi.WebService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Day11Activity extends AppCompatActivity {

    private Button mButton;

    @Inject
    WebService mWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day11);


        mButton = (Button) findViewById(R.id.click_btn);
        mButton.setOnClickListener((v) -> {
            mWebService.getUser("").enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.e("Day11Activity", "onResponse: " + response.body() );
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Day11Activity", "onFailure: " + t.getMessage() );
                }
            });
        });
    }
}
