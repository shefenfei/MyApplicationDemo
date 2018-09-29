package com.fenfei.myapplicationdemo.interfaces;

import android.arch.paging.DataSource;

import com.fenfei.myapplicationdemo.beans.User;
import com.fenfei.myapplicationdemo.beans.UserEntity;
import com.fenfei.myapplicationdemo.beans.UserResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by shefenfei on 2016/11/17.
 */

public interface FileDownloadService {

    //appcilent/microschool_v1.1.1.apk
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);


    @GET("/v1/home/getUserInfo")
    Flowable<UserResponse> getUserInfo(@Query("id") String id);

    /**
     * 测试
     * @return
     */
    @GET("/smile/initIndex.do")
    Call<ResponseBody> test();

    @GET("/index.html")
    Call<ResponseBody> index();

    @GET
    Call<ResponseBody> testSmileRequest(@Url String url);

    @POST("/url/xxx")
    Call<ResponseBody> testBody(@Body String json);

    //currentPage=1&pageSize=10
    @GET("v1/courses")
    Call<ResponseBody> listAll(@Query("accountId") String accountId ,
                               @Query("currentPage") int currentPage ,
                               @Query("pageSize") int pageSize);


    @GET
    Call<ResponseBody> dynamicUrl(@Url String url, @Query("citypinyin") String city);


    @GET("getQuestionInfo")
    Call<ResponseBody> getQuestionInfo();


    @POST("user/home")
    Call<ResponseBody> getUserInfoByJson(@Body String json);



    @GET("/ribbon/")
    Observable<User> getUserInfo();

    @GET
    Observable<String> fetchInfo();


//    params.put("appid", UrlContans.APP_ID);
//    params.put("secret", UrlContans.SECRET);
//    params.put("grant_type", UrlContans.GRANT_TYPE);
    //获取accessToken
    String URL_ACCESS_TOKEN = "token?";
    @GET(value = URL_ACCESS_TOKEN)
    Observable<String> fetchToken(@Query("appid") String appid ,
                                  @Query("secret") String secret,
                                  @Query("grant_type") String grant_type);



    @GET("/loadUsers")
    DataSource.Factory<Integer , UserEntity> loadUsers();


}
