package com.fenfei.aspectjlib;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by shefenfei on 2017/5/19.
 */

public class TestController {

    public Consumer<String> mConsumer = new Consumer<String>() {
        @Override
        public void accept(@NonNull String s) throws Exception {
            Log.e("TAG", "accept: "+  s );
        }
    };
}
