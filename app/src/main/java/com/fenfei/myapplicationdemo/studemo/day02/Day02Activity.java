package com.fenfei.myapplicationdemo.studemo.day02;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenfei.myapplicationdemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day02Activity extends AppCompatActivity {

    String TAG = "Day02Activity";

    @TargetApi(24)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day02);

        String params = "privatekey=smartcampus2017&paymentAccountId=ff8080815e73a27c015e73f193e20019";

        Map<String,String> paraMap = new HashMap<String,String>();
        paraMap.put("total_fee","200");
        paraMap.put("code" , "jahhahah");
        paraMap.put("appid", "wxd678efh567hg6787");
        paraMap.put("body", "充值中心");
        paraMap.put("out_trade_no","20150806125346");
        String r = formatUrl(paraMap);
        Log.e(TAG, "onCreate: " + r );


        TreeMap<String , String> treeMap = new TreeMap<>(String::compareTo);

        treeMap.put("total_fee","200");
        treeMap.put("code" , "jahhahah");
        treeMap.put("appid", "wxd678efh567hg6787");
        treeMap.put("body", "充值中心");
        treeMap.put("out_trade_no","20150806125346");

        treeMap.forEach((s, s2) -> {
            Log.e(TAG, "onCreate: " + s + "..." + s2 );
        });
    }


    private String formatUrl(Map<String , String> params) {

        List<Map.Entry<String , String>> map = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(map, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.toString());
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String , String> item : map) {
            if (!item.getKey().equals("")) {
                String key = item.getKey();
                String value = item.getValue();
                stringBuilder.append(key + "=" + value);
            }
            stringBuilder.append("&");
        }

        String result = stringBuilder.toString();
        if (!result.isEmpty()) {
            result = result.substring(0 , result.length() - 1);
        }

        return result;
    }
}
