package com.fenfei.myapplicationdemo.utils;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by shefenfei on 2017/11/9.
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
