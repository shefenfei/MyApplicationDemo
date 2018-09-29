package com.fenfei.myapplicationdemo.studemo.day07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenfei.myapplicationdemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基础的算法复习
 */
public class Day07Activity extends AppCompatActivity {

    private String TAG = "Day07Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day07);

        Integer[] arrs = {1, 100, 2, 98, 3, 34, 0, -1, 20, 167, 22, 21, 13};

//        Thread.yield();
//        quickSort(arrs , 0 , arrs.length - 1);

//        insertSort(arrs);
        bubbleSort(arrs);

        for (Integer i : arrs) {
//            Log.e(TAG, "插入排序后：onCreate: " + i );
        }


//        ZXingView zXingView = new ZXingView(this ,null);
        

        Moive moive = Moive.newBuilder().withTitle("").withDate("").build();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i< 10; i ++) {
//            new Thread(new MyTask("开启线程 " + Thread.currentThread().getName() + "执行任务: " + i)).start();
            executorService.execute(new MyTask("开启线程 ：" + i));
        }

        executorService.shutdown();

    }


    /**
     * 插入排序算法 , 核心要领： 空哪个元素插入哪个元素
     * @param nums
     */
    public void insertSort(Integer[] nums) {
        int temp = 0;
        int j = 0;

        for (int k = 0 ; k < nums.length ; k ++) {
            temp = nums[k];
            j = k -1;

            while (j >= 0 && temp < nums[j]) { //要跟前一个数据比较
                nums[j + 1] = nums[j];
                j -- ;
            }

            nums[j + 1] = temp;
        }
    }


    /**
     * 冒泡排序
     * 原理是两两交换,按照从小到大或者从大到小的顺序进行交换,
     * 这样一趟过去后,最大或最小的数字被交换到了最后一位,
     * @param nums
     */
    public void bubbleSort(Integer[] nums) {
        for (int i = 0; i< nums.length - 1 ; i++) { //最多比 n-1 次
            for (int j = 0 ; j < nums.length - 1 ; j ++) { //相临的两个数据比较
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 快速排序
     *
     *
     * @param num
     * @param start
     * @param end
     */
    public void quickSort(Integer[] num, int start, int end) {
        if (num == null || start > end) {
            return;
        }

        int tmp = num[start];
        int i = start;
        int j = end;

        while (i < j) {
            while (i < j && num[end] > tmp) {
                j--;
            }

            if (i < j) {
                num[i++] = num[j];
            }

            while (i < j && num[i] < tmp) {
                i++;
            }

            if (i < j) {
                num[j--] = num[i];
            }
        }

        num[i] = tmp;
        quickSort(num, start, i - 1);
        quickSort(num, i + 1, end);
    }


    class MyTask implements Runnable {


        public MyTask(String startMsg) {
            Log.e(TAG, "MyTask: " + startMsg );
        }

        @Override
        public void run() {
            for (int i = 0; i < 5 ; i ++) {
                Log.e(TAG, "run: " + i );
                Thread.yield();
            }

            Log.e(TAG, "run: 执行完成" );
        }
    }


}
