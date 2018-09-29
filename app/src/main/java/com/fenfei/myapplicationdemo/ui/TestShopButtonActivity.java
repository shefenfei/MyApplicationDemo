package com.fenfei.myapplicationdemo.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;
import com.fenfei.myapplicationdemo.services.IPCService;
import com.fenfei.myapplicationdemo.views.ShopButton;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestShopButtonActivity extends AppCompatActivity {

    private static String TAG = "TestShopButtonActivity";

    private ShopButton mShopButton_;
    private Button test;
    private ScrollView mScrollView_;
    private ImageView glide_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shop_button);

        glide_img = (ImageView) findViewById(R.id.glide_img);
//
//        final FileDownloadService apiService = ServiceGenerator.generate(this)
//                .setEndpoint("http://wdzp.topetrain.com.cn/api/")
//                .getApiService(FileDownloadService.class);
//
//

//        mShopButton_ = (ShopButton)findViewById(R.id.shop_button);
//        mShopButton_.setOnAddListener(new ShopButton.OnAddListener() {
//            @Override
//            public void onAdd() {
//                Log.e(TAG, "onAdd: 加" );
//            }
//
//            @Override
//            public void onReduce() {
//                Log.e(TAG, "onReduce: 减"  );
//            }
//        });

//
//        test = (Button) this.findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                loadData(apiService);
//                connectService();
//            }
//        });

        testGilde();
    }

    private void testGilde() {
        String u1 = "http://oc00s4631.bkt.clouddn.com/course/20170113160530376/app8.png";
        String url = "http://inews.gtimg.com/newsapp_match/0/1300081099/0";
//        Glide.with(this).load(Uri.parse(url)).into(glide_img);

        RequestManager requestManager = Glide.with(this);
        requestManager.load(Uri.parse(u1)).into(glide_img);
    }

    private void connectService() {
        Intent intent = new Intent(this , IPCService.class);
        MyServiceConnection connection = new MyServiceConnection();
        bindService(intent , connection , BIND_AUTO_CREATE);
    }

    private void loadData(FileDownloadService apiService) {
        Call<ResponseBody> call = apiService.listAll("000000005aa2b749015aa2f195360021", 1, 10);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String headers = response.headers().toString();
                        Log.e(TAG, "header: " + headers );
                        String body = response.body().string();
                        Log.e(TAG, "onResponse: " + body );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private class  MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message message = Message.obtain();
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private static final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: " + "收到service 的消息" );
        }
    };

    private final static Messenger msger = new Messenger(mHandler);

}
