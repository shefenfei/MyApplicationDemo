package com.fenfei.myapplicationdemo.studemo.day05.absfactory;

import java.util.Map;

/**
 * Created by shefenfei on 2017/12/25.
 */

public interface AbstractTarget {

     Map<String , String> buildMsgAccount(String tenantId);
}
