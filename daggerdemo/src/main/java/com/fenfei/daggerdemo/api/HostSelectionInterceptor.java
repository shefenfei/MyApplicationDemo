package com.fenfei.daggerdemo.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shefenfei on 2018/2/22.
 */
public class HostSelectionInterceptor implements Interceptor {

    private volatile String host;

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (host != null) {
            HttpUrl httpUrl = request.url().newBuilder()
                    .host(host)
                    .build();

            request = request.newBuilder()
                    .url(host)
                    .build();
        }
        return chain.proceed(request);
    }
}
