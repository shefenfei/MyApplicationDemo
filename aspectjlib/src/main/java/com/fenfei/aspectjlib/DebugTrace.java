package com.fenfei.aspectjlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shefenfei on 2017/2/3.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR , ElementType.METHOD})
public @interface  DebugTrace {

}
