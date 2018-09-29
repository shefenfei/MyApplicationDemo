package com.fenfei.myapplicationdemo.designpattern.factroypattern;

import android.util.Log;

/**
 * Created by shefenfei on 2016/11/21.
 */
//以便生成不同类型的pizza
public class Pizza {

    private String TAG = "Pizza";

    private String name;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        Log.e(TAG, "setName: " + name);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void prepare() {
        Log.e(TAG, "prepare:");
    }

    public void bake() {
        Log.e(TAG, "bake: " );;
    }

    public void cut() {
        Log.e(TAG, "cut: " );
    }

    public void box() {
        Log.e(TAG, "box: " );
    }
}
