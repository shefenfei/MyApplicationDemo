package com.fenfei.myapplicationdemo.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.Question;
import com.fenfei.myapplicationdemo.beans.User;
import com.fenfei.myapplicationdemo.http.ServiceGenerator;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView image;

    String TAG = "QuestionActivity";
    String apiUrl = "http://10.15.128.26:8080/ffan/rs/Rest/";
    FileDownloadService apiService;

    Gson mGson = new Gson();
    
    String str = "";

    double totals = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        textView = (TextView) findViewById(R.id.html_text);
        image = (ImageView) findViewById(R.id.img);



        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionActivity.this , NFCActivity.class));
            }
        });
        
        
        
        

        apiService = ServiceGenerator.generate(this).setEndpoint(apiUrl).getApiService(FileDownloadService.class);
        Call<ResponseBody> call = apiService.getQuestionInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    double total = 0.0;

                    for (double i=0 ; i< 10; i++) {
                        total += i;
                    }
//                    setDays(total);

                    totals = total;


                    try {
                        String resp = response.body().string();
                        Gson gson = new Gson();
                        Question q = gson.fromJson(resp , Question.class);
                        str = q.getQuestion();
                        textView.setText(Html.fromHtml(q.getQuestion() , new NetworkImageGetter() , null));
                        Log.e(TAG, "onResponse: " + resp );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });


        User user = new User();
        user.setAge(1);
        user.setUsername("shefenfei");
        String json = mGson.toJson(user);
        Call<ResponseBody> call1 = apiService.testBody(json);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // TODO: 2017/10/24  
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


//        Observable<User> observable = apiService.getUserInfo();
//        observable.observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Action1<User>() {
//                    @Override
//                    public void call(User user) {
//
//                    }
//                });
    }

    Html.ImageGetter imageGetter = new Html.ImageGetter() {
        Drawable drawable;
        @Override
        public Drawable getDrawable(String source) {
            Log.e(TAG, "getDrawable: " + source);
            return drawable;
        }

        private void extraBitmap() {
            Glide.with(QuestionActivity.this).load(Uri.parse("https://img1.ffan.com/orig/T1OTJTB5E_1RCvBVdK"))
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Log.e(TAG, "onResourceReady: " + "转化bitmap" );
                            Drawable bitmapDrawable = new BitmapDrawable(resource);
                            drawable = bitmapDrawable;

                            final Rect rect = new Rect(0 , 0, 100 ,100);
                            drawable.setBounds(rect);
                        }
                    });
        }
    };



    private void setDays(double days) {
        totals = days;
    }


    /**
     * 本地图片
     * @author Susie
     */
    private final class LocalImageGetter implements Html.ImageGetter{

        @Override
        public Drawable getDrawable(String source) {
            // 获取本地图片
            Drawable drawable = Drawable.createFromPath(source);
            // 必须设为图片的边际,不然TextView显示不出图片
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            // 将其返回
            return drawable;
        }
    }
    /**
     * 项目资源图片
     * @author Susie
     */
    private final class ProImageGetter implements Html.ImageGetter{

        @Override
        public Drawable getDrawable(String source) {
            // 获取到资源id
            int id = Integer.parseInt(source);
            Drawable drawable = getResources().getDrawable(id);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }
    }
    /**
     * 网络图片
     * @author Susie
     */
    private final class NetworkImageGetter implements Html.ImageGetter{

        @Override
        public Drawable getDrawable(String source) {
            source = "https://img1.ffan.com/orig/T1fGLTBgdQ1RCvBVdK";
            Drawable drawable = null;
            AsyncLoadNetworkPic networkPic = new AsyncLoadNetworkPic();
            networkPic.execute(source);
            return drawable;
        }
    }
    /**
     * 加载网络图片异步类
     * @author Susie
     */
    private final class AsyncLoadNetworkPic extends AsyncTask<String, Integer, Void>{

        @Override
        protected Void doInBackground(String... params) {
            // 加载网络图片
            loadNetPic(params);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // 当执行完成后再次为其设置一次
//            textView.setText(Html.fromHtml(htmlThree, mImageGetter, null));
            textView.setText(Html.fromHtml(str , null , null));
            EditText editText = null;
        }

        /**加载网络图片*/
        private void loadNetPic(String... params) {
            String path = params[0];

            File file = new File(Environment.getExternalStorageDirectory(), "");

            InputStream in = null;

            FileOutputStream out = null;

            try {
                URL url = new URL(path);

                HttpURLConnection connUrl = (HttpURLConnection) url.openConnection();

                connUrl.setConnectTimeout(5000);

                connUrl.setRequestMethod("GET");

                if(connUrl.getResponseCode() == 200) {

                    in = connUrl.getInputStream();

                    out = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];

                    int len;

                    while((len = in.read(buffer))!= -1){
                        out.write(buffer, 0, len);
                    }
                } else {
                    Log.i(TAG, connUrl.getResponseCode() + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if(in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
