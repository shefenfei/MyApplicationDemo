package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.http.ServiceGenerator;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareActionActivity extends AppCompatActivity {

    private String TAG = "ShareActionActivity";

    private String apiBaseUrl = "http://www.baidu.com";
    private String newApiBaseUrl = "http://www.sina.com";

    ShareActionProvider shareActionProvider;
    Button mShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_action);


//        MyReceiver myReceiver = new MyReceiver();
//        IntentFilter intentFilter  = new IntentFilter("com.shefenfei.bd");
//        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver , intentFilter);


        mShareButton = (Button) findViewById(R.id.share);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                handleShareIntent(intent);
            }
        });

        FileDownloadService apiService = ServiceGenerator.generate(this)
                .setEndpoint(apiBaseUrl)
                .getApiService(FileDownloadService.class);

//        ServiceGenerator.changeApiBaseUrl(newApiBaseUrl);

        apiService.index().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.isSuccessful() ? response.code() : -1;
                Log.e(TAG, "onResponse: " + code);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


        apiService.testSmileRequest("http://url.cn/43ZCMpP").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.isSuccessful() ? response.code() : -1;
                Log.e(TAG, "onResponse: " + code);
                if (code == 200) {
                    try {
                        String result = response.body().string();
                        Log.e(TAG, "onResponse: " + result);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_share_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            case R.id.menu_item_share:
                Log.e(TAG, "onOptionsItemSelected: ");
                break;
        }
        return true;
    }


    @TargetApi(19)
    private void handleShareIntent(Intent intent) {
        WebView webView = null;
        String scriptString = null;
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(intent);
        }
        webView.loadUrl("javascript:scriptString"); //其中 scriptString 为 Javascript 代码

        webView.evaluateJavascript(scriptString, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });//其中 scriptString 为 Javascript 代码，ValueCallback 的用来获取 Javascript 的执行结果。这是一个异步掉用。

        webView.addJavascriptInterface(new JavaProxy(), "java_proxy");
    }

    class JavaProxy{
        //注意这里的注解。出于安全的考虑，4.2 之后强制要求，不然无法从 Javascript 中发起调用
        @JavascriptInterface
        public void javaFn(){
            //xxxxxx
        };
    }

}
