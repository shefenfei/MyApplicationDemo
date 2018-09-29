package com.fenfei.myapplicationdemo.studemo.day05.absfactory;

import java.util.Map;

/**
 * Created by shefenfei on 2017/12/25.
 */

public class TvModel implements AbstractTarget {

    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public Map<String, String> buildMsgAccount(String tenantId) {
        return null;
    }
}
