package com.fenfei.daggerdemo.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shefenfei on 2018/1/25.
 */
public interface DaggerApiService {

    @GET("/user/{userId}")
    Call<String> getUserInfo(@Path("userId") String uid);
}
