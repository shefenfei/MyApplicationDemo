package com.fenfei.myapplicationdemo.studemo.day05.absfactory;

/**
 * Created by shefenfei on 2017/12/25.
 */

public interface AbstractMsgChannelFactory {

    /**
     * 通过亿美发送短信
     * @return
     */
    String sendByYM();

    //可拓展其他的渠道

}
