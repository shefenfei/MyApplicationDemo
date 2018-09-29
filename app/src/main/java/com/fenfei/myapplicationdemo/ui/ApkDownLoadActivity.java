package com.fenfei.myapplicationdemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.Weather;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;
import com.fenfei.myapplicationdemo.interfaces.ProgressListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApkDownLoadActivity extends AppCompatActivity implements ProgressListener{

    private String TAG = "ApkDownLoadActivity";
    ProgressDialog mProgressDialog;

    private ProgressListener mProgressListener_;

    //http://www.shsmile.com.cn/appcilent/microschool_v1.1.1.apk",
    public static final String BASE_URL = "http://www.shsmile.com.cn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_down_load);

        Button button = (Button) findViewById(R.id.load_apk);
        buildDialog();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProgressListener_ = (ProgressListener)this;

        parseJson();

        final FileDownloadService downloadService = retrofit.create(FileDownloadService.class);
        final Call<ResponseBody> call = downloadService.downloadFileWithDynamicUrlSync("/appcilent/microschool_v1.1.1.apk");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                retrofit(call);
//                mProgressDialog.show();
                Call<ResponseBody> call1 = downloadService.dynamicUrl("http://apistore.baidu.com/microservice/weather" , "beijing");
                call1.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                Log.e(TAG, "onResponse: " + response.body().string() );
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
            }
        });





    }

    private void parseJson() {
        String json = "{\n" +
                "    \"msg\": \"success\",\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"airCondition\": \"良\",\n" +
                "            \"city\": \"上海\",\n" +
                "            \"coldIndex\": \"低发期\",\n" +
                "            \"date\": \"2017-03-29\",\n" +
                "            \"distrct\": \"上海\",\n" +
                "            \"dressingIndex\": \"夹衣类\",\n" +
                "            \"exerciseIndex\": \"非常适宜\",\n" +
                "            \"future\": [\n" +
                "                {\n" +
                "                    \"date\": \"2017-03-29\",\n" +
                "                    \"dayTime\": \"多云\",\n" +
                "                    \"night\": \"多云\",\n" +
                "                    \"temperature\": \"16°C / 11°C\",\n" +
                "                    \"week\": \"今天\",\n" +
                "                    \"wind\": \"北风 小于3级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-03-30\",\n" +
                "                    \"dayTime\": \"小雨\",\n" +
                "                    \"night\": \"小雨\",\n" +
                "                    \"temperature\": \"14°C / 9°C\",\n" +
                "                    \"week\": \"星期四\",\n" +
                "                    \"wind\": \"东风 3～4级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-03-31\",\n" +
                "                    \"dayTime\": \"阴\",\n" +
                "                    \"night\": \"多云\",\n" +
                "                    \"temperature\": \"14°C / 9°C\",\n" +
                "                    \"week\": \"星期五\",\n" +
                "                    \"wind\": \"北风 3～4级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-04-01\",\n" +
                "                    \"dayTime\": \"多云\",\n" +
                "                    \"night\": \"多云\",\n" +
                "                    \"temperature\": \"16°C / 8°C\",\n" +
                "                    \"week\": \"星期六\",\n" +
                "                    \"wind\": \"北风 小于3级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-04-02\",\n" +
                "                    \"dayTime\": \"晴\",\n" +
                "                    \"night\": \"晴\",\n" +
                "                    \"temperature\": \"19°C / 9°C\",\n" +
                "                    \"week\": \"星期日\",\n" +
                "                    \"wind\": \"西风 小于3级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-04-03\",\n" +
                "                    \"dayTime\": \"多云\",\n" +
                "                    \"night\": \"多云\",\n" +
                "                    \"temperature\": \"21°C / 11°C\",\n" +
                "                    \"week\": \"星期一\",\n" +
                "                    \"wind\": \"南风 小于3级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-04-04\",\n" +
                "                    \"dayTime\": \"少云\",\n" +
                "                    \"night\": \"阴天\",\n" +
                "                    \"temperature\": \"18°C / 13°C\",\n" +
                "                    \"week\": \"星期二\",\n" +
                "                    \"wind\": \"东南偏南风 4级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-04-05\",\n" +
                "                    \"dayTime\": \"多云\",\n" +
                "                    \"night\": \"阵雨\",\n" +
                "                    \"temperature\": \"18°C / 13°C\",\n" +
                "                    \"week\": \"星期三\",\n" +
                "                    \"wind\": \"东南风 4级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-04-06\",\n" +
                "                    \"dayTime\": \"雨\",\n" +
                "                    \"night\": \"阵雨\",\n" +
                "                    \"temperature\": \"19°C / 12°C\",\n" +
                "                    \"week\": \"星期四\",\n" +
                "                    \"wind\": \"东南偏南风 4级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"2017-04-07\",\n" +
                "                    \"dayTime\": \"阴天\",\n" +
                "                    \"night\": \"阴天\",\n" +
                "                    \"temperature\": \"18°C / 13°C\",\n" +
                "                    \"week\": \"星期五\",\n" +
                "                    \"wind\": \"东南风 4级\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"humidity\": \"湿度：63%\",\n" +
                "            \"pollutionIndex\": \"78\",\n" +
                "            \"province\": \"上海\",\n" +
                "            \"sunrise\": \"05:47\",\n" +
                "            \"sunset\": \"18:11\",\n" +
                "            \"temperature\": \"15℃\",\n" +
                "            \"time\": \"13:40\",\n" +
                "            \"updateTime\": \"20170329135737\",\n" +
                "            \"washIndex\": \"不适宜\",\n" +
                "            \"weather\": \"多云\",\n" +
                "            \"week\": \"周三\",\n" +
                "            \"wind\": \"北风3级\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"retCode\": \"200\"\n" +
                "}";

        Gson gson = new Gson();
        Weather weather = gson.fromJson(json , Weather.class);
        Log.e(TAG, "parseJson: " + weather.getMsg() + "...." + weather.getRetCode() + "..." + weather.getResult().size());

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("result");
        for (int i=0;i < jsonArray.size(); i++) {
            JsonElement element = jsonArray.get(i);
            String e = element.getAsJsonObject().get("airCondition").getAsString();
            Log.e(TAG, "parseJson: " + e );
        }
    }

    private void buildDialog() {
        mProgressDialog = new ProgressDialog(ApkDownLoadActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
        mProgressDialog.setTitle("下载中...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setTitle("正在下载");
        mProgressDialog.setMessage("请稍候...");
        mProgressDialog.setProgress(0);
        mProgressDialog.setMax(100);
    }

    private void retrofit(Call<ResponseBody> call) {
        call.clone().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "server contacted and has file");
                    long filesize = response.body().contentLength();
                    mProgressDialog.setMax((int)filesize);
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                    if (writtenToDisk) {
                        update();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "error");
                mProgressListener_.onProgress(20);
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            Log.d("DownloadFile", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;

            try {
                //文件大小
                long fileSize = body.contentLength();
                long downLoadSize = 0;

                byte[] buffer = new byte[2048];
                in = body.byteStream();
                out = new FileOutputStream(getExternalFilesDir(null) + File.separator + "microschool.apk");

                //记录下载文件的大小
                int readSize;

                while ((readSize = in.read(buffer)) != -1) {
                    downLoadSize += readSize;
                    out.write(buffer , 0 , readSize);
                    mProgressListener_.onProgress((int) ((downLoadSize * 100) / fileSize));
                }
            }
            catch (IOException e) {
                Log.d("DownloadFile",e.toString());
                return false;
            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            return true;

        } catch (IOException e) {
            Log.d("DownloadFile",e.toString());
            return false;
        }
    }

    //安装文件，一般固定写法
    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(getExternalFilesDir(null) + File.separator, "microschool.apk")),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void onProgress(int progress) {
        mProgressDialog.setProgress(progress);
    }
}
