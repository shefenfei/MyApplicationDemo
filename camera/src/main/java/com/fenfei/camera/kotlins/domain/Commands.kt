package com.fenfei.camera.kotlins.domain

/**
 * Created by shefenfei on 2017/6/1.
 */
public interface Commands<T> {

    fun execute(): T


}
