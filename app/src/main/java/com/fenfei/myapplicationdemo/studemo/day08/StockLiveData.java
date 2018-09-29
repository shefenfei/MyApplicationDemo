package com.fenfei.myapplicationdemo.studemo.day08;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by shefenfei on 2018/1/18.
 *  如果观察者的生命周期处于STARTED或RESUMED状态，
 *  则LiveData将认为观察者处于活动状态。以下样本代码说明了如何扩展LiveData类：
 */
public class StockLiveData extends LiveData<BigDecimal> {

    private String TAG = "StockLiveData";

    private static StockLiveData sInstance;
    private StockManager mStockManager;

    private SimplePriceListener mPriceListener = this::setValue;

    public static StockLiveData get(String symbol) {
        if (sInstance == null) {
            sInstance = new StockLiveData(symbol);
        }
        return sInstance;

    }

    private StockLiveData(String symbol) {
        mStockManager = new StockManager(symbol);
    }

    /**
     * 活动状态 加载数据
     */
    @Override
    protected void onActive() {
        super.onActive();

        Log.e(TAG, "onActive: " );
        mStockManager.requestPriceUpdates(mPriceListener);
    }


    /**
     * 非活动状态 不加载数据
     */
    @Override
    protected void onInactive() {
        super.onInactive();
        Log.e(TAG, "onInactive: " );
        mStockManager.removeUpdates(mPriceListener);
    }
}
