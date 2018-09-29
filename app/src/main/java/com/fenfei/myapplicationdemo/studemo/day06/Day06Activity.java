package com.fenfei.myapplicationdemo.studemo.day06;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.fenfei.myapplicationdemo.R;

public class Day06Activity extends AppCompatActivity {

    String TAG = "Day06Activity";

    private ListView mListView;

    private boolean IS_LOADING = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day06);

        mListView = (ListView) findViewById(R.id.listview);

        initAdapter(mListView);
    }

    private void initAdapter(ListView listView) {
        Myadapter myadapter = new Myadapter(this , R.layout.item_listview_demo);
        listView.setAdapter(myadapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //手指在上面拖着动
                    case SCROLL_STATE_TOUCH_SCROLL:
                        IS_LOADING = false;
                        Log.e(TAG, "SCROLL_STATE_TOUCH_SCROLL: " );
                        break;

                    //滑动一下手指松开惯性滑动
                    case SCROLL_STATE_FLING:
                        IS_LOADING = false;
                        Log.e(TAG, "SCROLL_STATE_FLING: " );
                        break;

                    //滑动结束，优化listview的时候可以考虑在这里加载图片等费时的操作
                    case SCROLL_STATE_IDLE:
                        Log.e(TAG, "SCROLL_STATE_IDLE: " );
                        int start = mListView.getFirstVisiblePosition();
                        int end = mListView.getLastVisiblePosition();
                        if (end >= mListView.getCount()) {
                            end = mListView.getCount() - 1;
                        }
                        //展示start - end之间的图片
                        IS_LOADING = true;
                        loadImageFromStart2End(start , end);
                        // TODO: 2018/1/8 加载指定位置的图片
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void loadImageFromStart2End(int start, int end) {
        for (int index = start ; index < end ; index ++) {

        }
    }

    class Myadapter extends ArrayAdapter<String> {

        int resId = -1;

        public Myadapter(@NonNull Context context, int resource) {
            super(context, resource);
            resId = resource;
        }

        @Override
        public int getCount() {
            return 30;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
                viewHolder = new ViewHolder();

                viewHolder.mButton1 = convertView.findViewById(R.id.btn1);
                viewHolder.mButton2 = convertView.findViewById(R.id.btn2);
                viewHolder.mButton3 = convertView.findViewById(R.id.btn3);
                viewHolder.mImageView = convertView.findViewById(R.id.imageView);

                viewHolder.mButton1.setOnClickListener(viewHolder);
                viewHolder.mButton2.setOnClickListener(viewHolder);
                viewHolder.mButton3.setOnClickListener(viewHolder);

                loadImage(position , viewHolder.mImageView);

                viewHolder.setPosition(position);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder implements View.OnClickListener {

            Button mButton1;
            Button mButton2;
            Button mButton3;

            ImageView mImageView;

            int position = -1;

            public void setPosition(int position) {
                this.position = position;
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn1:
                        Log.e(TAG, "onClick:btn1 " + position );
                        break;

                    case R.id.btn2:
                        Log.e(TAG, "onClick:btn2 " + position );
                        break;

                    case R.id.btn3:
                        Log.e(TAG, "onClick:btn3 " + position );
                        break;
                }
            }
        }
    }

    private void loadImage(int position, ImageView imageView) {
        Glide.with(Day06Activity.this)
                .load(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515129437076&di=8e84feba21a08c3d16fa9c92177734d7&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F140613%2F240466-1406130QP479.jpg"))
                .into(imageView);
    }



}
