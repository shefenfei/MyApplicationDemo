package com.fenfei.petshome.utils

/**
 * Created by shefenfei on 2017/7/7.
 */
class ServiceGenerator {

    object HttpService {
        fun setEndPoint(): HttpService {
            return this
        }
    }

    fun setEndPoint(): HttpService {
        return ServiceGenerator.HttpService;
    }

}