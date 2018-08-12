package com.fenfei.criminalintent.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Created by shefenfei on 2018/4/12.
 */

public class MyService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Channel channel;
        Selector selector;

        FileChannel fileChannel;
//        fileChannel.t
    }
}
