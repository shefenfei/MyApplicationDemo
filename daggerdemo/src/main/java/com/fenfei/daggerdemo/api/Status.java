package com.fenfei.daggerdemo.api;

/**
 * Created by shefenfei on 2018/1/31.
 */

public enum Status {
    SUCCESS(200), ERROR(500) , LOADING(2);

    int code;

    Status(int code) {
        this.code = code;
    }

    public Status getMessage(String code) {
        return Status.valueOf(code);
    }
}
