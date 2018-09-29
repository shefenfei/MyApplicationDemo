package com.fenfei.myapplicationdemo.studemo.day05.absfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shefenfei on 2017/12/25.
 */

public class YmMsgAccount implements AbstractTarget {

    @Override
    public Map<String, String> buildMsgAccount(String tenantId) {
        Map<String , String> map = new HashMap<>();
        map.put("appid" , "secret");
        map.put("message", "亿美");
        return map;
    }
}
