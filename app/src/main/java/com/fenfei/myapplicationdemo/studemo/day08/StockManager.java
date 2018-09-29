package com.fenfei.myapplicationdemo.studemo.day08;

import java.math.BigDecimal;

/**
 * Created by shefenfei on 2018/1/18.
 * 库存管理器
 */
public class StockManager {

    private String symbol;

    public StockManager(String s) {
        this.symbol = s;
    }

    public void requestPriceUpdates(SimplePriceListener listener){
        //load data
        BigDecimal bigDecimal = new BigDecimal("100");
        listener.onPriceChanged(bigDecimal);
    }


    public void removeUpdates(SimplePriceListener listener) {
        BigDecimal bigDecimal = new BigDecimal("200");
        listener.onPriceChanged(bigDecimal);
    }

}
