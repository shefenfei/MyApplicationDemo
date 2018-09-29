package com.fenfei.myapplicationdemo.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shefenfei on 2017/3/29.
 */

public class Weather implements Serializable {

    String msg;
    List<Result> result;
    String retCode;

    public String getMsg() {
        return msg;
    }

    public List<Result> getResult() {
        return result;
    }

    public String getRetCode() {
        return retCode;
    }

    class Result implements Serializable{
         String airCondition;
         String city;
         String coldIndex;
         String date;
         String distrct;
         String dressingIndex;
         String exerciseIndex;
         List<Future> future;
    }

    class Future implements Serializable {
        String date;
        String dayTime;
        String night;
        String temperature;
        String week;
        String wind;
    }
}
