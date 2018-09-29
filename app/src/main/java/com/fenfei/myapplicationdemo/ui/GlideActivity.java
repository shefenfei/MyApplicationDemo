package com.fenfei.myapplicationdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.fenfei.myapplicationdemo.R;

import java.util.Arrays;
import java.util.List;

public class GlideActivity extends AppCompatActivity {

    private ListView mListView_;
    String[] urls = {"http://www.topetrain.com.cn/img/data/c11.png",
            "http://www.topetrain.com.cn/img/data/c12.png",
            "http://www.topetrain.com.cn/img/data/c11.png",
            "http://www.topetrain.com.cn/img/data/c12.png"
    };
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        mListView_ = (ListView) findViewById(R.id.glide_list);
        list = Arrays.asList(urls);
        MyAdapter adapter = new MyAdapter(this, R.layout.item_glide, list);
        mListView_.setAdapter(adapter);
        mListView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goDetail(position);
            }
        });
    }

    private void goDetail(int position) {
        Intent intent = new Intent(this , DetailActivity.class);
        intent.putExtra("url" , list.get(position));
        startActivity(intent);
    }


    class MyAdapter extends ArrayAdapter<String> {

        LayoutInflater mInflater_;

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mInflater_ = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new MyViewHolder();
                convertView = mInflater_.inflate(R.layout.item_glide, parent, false);
                viewHolder.mImageView_ = (ImageView) convertView.findViewById(R.id.item_glide_img);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (MyViewHolder) convertView.getTag();
            }
            Glide.with(GlideActivity.this)
                    .load(Uri.parse(list.get(position)))
                    .into(viewHolder.mImageView_);
            return convertView;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    class MyViewHolder {
        ImageView mImageView_;
    }

}
