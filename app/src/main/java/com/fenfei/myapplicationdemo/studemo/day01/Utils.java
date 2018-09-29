package com.fenfei.myapplicationdemo.studemo.day01;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by shefenfei on 2017/11/21.
 */

public class Utils {

    @TargetApi(24)
    public static List<String> mergeList(@NonNull List<String> list1, @NonNull List<String> list2, String TAG) {
        List<String> results = new ArrayList<>();
        int count1 = list1.size();
        int count2 = list2.size();

        if (count1 > count2) {
            list2.parallelStream().filter(s -> list1.contains(s)).forEach(s -> Log.e(TAG, "mergeList: list1 大" + s ));
        }else if (count2 > count1){
            list1.parallelStream().filter(s -> list2.contains(s)).forEach(s -> Log.e(TAG, "mergeList: list2 大" + s ));
        }else {
            list1.parallelStream().filter(s -> list2.contains(s)).forEach(s -> Log.e(TAG, "mergeList: 相等 " + s ));
        }


        test(list1 , list2);
        return results;
    }

    @TargetApi(24)
    private static void test(@NonNull List<String> list1, @NonNull List<String> list2) {
        Set<String> collect = list1.parallelStream()
                .filter(s -> list2.contains(s))
                .collect(Collectors.toSet());
        collect.parallelStream().forEach(s -> Log.e("TAG", "test: " + s ));
    }


    /**
     *
     * @param list1
     * @param list2
     * @param isIntersection 是否取交集
     */
    @TargetApi(24)
    public static Set<String> resultSet(List<String> list1 , List<String> list2 ,boolean isIntersection) {
        Set<String> result = new HashSet<>();

        if (isIntersection) { //交集
            result = list1.parallelStream()
                    .filter(s -> list2.contains(s))
                    .collect(Collectors.toSet());
        }else { //并集,去重
            list1.parallelStream().forEach(result::add);
            list2.parallelStream().forEach(result::add);
        }
        return result;
    }
}
