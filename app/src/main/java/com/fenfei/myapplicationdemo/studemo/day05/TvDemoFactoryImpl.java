package com.fenfei.myapplicationdemo.studemo.day05;

/**
 * Created by shefenfei on 2017/12/25.
 */
//找到了一家可以生产这个型号的电视的工厂
class TvDemoFactoryImpl implements DemoFactory {

    @Override
    public TvBean createChannel() {
        return new TvBean();
    }
}
