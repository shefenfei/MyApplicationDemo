package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.views.MyDemoAdapter;

import java.util.ArrayList;
import java.util.List;

public class TouchListviewActivity extends AppCompatActivity {

    private String TAG = "TouchListviewActivity";

    int lastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_demo);

        ListView listView = (ListView) findViewById(R.id.listview);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("" + i);
        }
        MyDemoAdapter adapter = new MyDemoAdapter(this, datas);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.empty_view));
        listView.setSelection(3);

        //listview对滑动的监听
        listView.setOnTouchListener((v, event) -> handleEvent(event));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        //停止滑动的时候
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        //正在滑动时
                        break;
                    case SCROLL_STATE_FLING:
                        //手指抛动，惯性继续滑动
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem, //第一个看见的item
                                 int visibleItemCount, //当前能看到的item的count
                                 int totalItemCount) { //总的item数
                //滑动的过程一会调用
                if (firstVisibleItem + visibleItemCount
                        == totalItemCount && totalItemCount > 0) {
                    //滑动到最后一行
                    Log.e(TAG, "onScroll:  到最后了" );
                }

                if (firstVisibleItem > lastVisibleItemPosition) {
                    //上滑
                }else if (firstVisibleItem < lastVisibleItemPosition) {
                    //下滑
                }

                lastVisibleItemPosition = firstVisibleItem;

            }
        });

        //可视区域里的最后一个位置
        listView.getLastVisiblePosition();
        //可视区域里的第一个位置
        listView.getFirstVisiblePosition();

//        listView.getLocationOnScreen(new int[]{1,1});
        listView.getLeft();
        listView.getRight();
        listView.getTop();
        listView.getBottom();

//        listView.getX();
    }

    private boolean handleEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "handleEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "handleEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "handleEvent: ACTION_UP");
                break;
        }
        return false;
    }

}
