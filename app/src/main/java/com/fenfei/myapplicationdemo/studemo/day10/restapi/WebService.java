package com.fenfei.myapplicationdemo.studemo.day10.restapi;

import com.fenfei.myapplicationdemo.studemo.day10.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shefenfei on 2018/1/19.
 *  fetch data utils
 */
public interface WebService {

    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     * @param uid
     * @return
     */
    @GET("/user/{user}")
    Call<User> getUser(@Path("user") String uid);
}
