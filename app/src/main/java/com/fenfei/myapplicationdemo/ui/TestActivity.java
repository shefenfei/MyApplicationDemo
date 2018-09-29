package com.fenfei.myapplicationdemo.ui;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.ResultResp;
import com.fenfei.myapplicationdemo.beans.User;
import com.fenfei.myapplicationdemo.http.ServiceGenerator;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {


    String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        http://10.15.130.31:8763/user/home
        FileDownloadService service = ServiceGenerator.generate(this)
                .setEndpoint("http://10.15.130.31:8763/").getApiService(FileDownloadService.class);


        Button button = (Button) findViewById(R.id.click_btn);
        button.setOnClickListener((view) -> {
            User user = new User();
            user.setUsername("shefenfei");
            user.setAge(27);
            user.setSex(1);
            user.setOK(false);
            Gson gson = new Gson();
            String json1 = gson.toJson(user);

            Call<ResponseBody> call = service.getUserInfoByJson(json1);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        String s = null;
                        try {
                            s = response.body().string();
                            Log.e(TAG, "onResponse: " + s);
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
        });


        String json = "{\n" +
                "  \"code\": 200,\n" +
                "  \"desc\": \"success\",\n" +
                "  \"body\": {\n" +
                "    \"teachingTeacher\": [\n" +
                "      \"app_t_t_leave\",\n" +
                "      \"app_t_t_weekWork\",\n" +
                "      \"app_t_t_repair\",\n" +
                "      \"app_t_t_maintain\",\n" +
                "      \"app_t_t_leaveApply\"\n" +
                "    ],\n" +
                "    \"teachingStudent\": [\n" +
                "      \"app_t_s_leave\",\n" +
                "      \"app_t_s_payment\",\n" +
                "      \"app_t_s_body\",\n" +
                "      \"app_t_s_strong\",\n" +
                "      \"app_t_s_activity\",\n" +
                "      \"app_t_s_awards\"\n" +
                "    ],\n" +
                "    \"teachingLeader\": [\n" +
                "      \"app_t_l_charge\",\n" +
                "      \"app_t_l_service\",\n" +
                "      \"app_t_l_stu_leave_s\",\n" +
                "      \"app_t_l_tea_leave_s\"\n" +
                "    ],\n" +
                "    \"teachingQuick\": [\n" +
                "      \"app_t_q_leave\",\n" +
                "      \"app_t_q_send_msg\",\n" +
                "      \"app_t_q_repair\",\n" +
                "      \"app_t_q_weekWork\"\n" +
                "    ]\n" +
                "  }\n" +
                "}";


        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        JsonObject object = jsonObject.getAsJsonObject("body");
        Log.e("TESTACTIVITY", "onCreate: " + object);

        JsonArray array = object.getAsJsonArray("teachingTeacher");
        ResultResp resultResp = new ResultResp();
        List<String> teachers = resultResp.getTeachingTeacher();
        for (JsonElement element : array) {
            String e = element.getAsString();
            teachers.add(e);
        }

        resultResp.setTeachingTeacher(teachers);
        Log.e("TESTACTIVITY", "onCreate: " + resultResp.getTeachingTeacher().toString());

    }








}
