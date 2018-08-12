package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.User;

import java.util.List;

/**
 * Created by shefenfei on 2018/3/15.
 */

public class MyDemoAdapter extends BaseAdapter {

    private List<String> datas;
    private LayoutInflater mLayoutInflater;
    private List<User> mUsers;

    public MyDemoAdapter(Context context , List<String> datas) {
        mLayoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (getItemViewType(position) == 2) {
                //消息发送者
            }else {
                //消息接收者
            }
            convertView = mLayoutInflater.inflate(R.layout.item_demo_list , null);
            viewHolder.mTextView = convertView.findViewById(R.id.textview1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText(datas.get(position));
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mUsers.get(position).getTYPE();
    }


    public final class ViewHolder {
        TextView mTextView;
        Button mButton;
    }

}
