package com.fenfei.myapplicationdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;

/**
 * Created by shefenfei on 2017/7/19.
 */
public class ChatBottomLayout extends LinearLayout implements View.OnClickListener {

    private TextView mVoiceView;
    private EditText mContentText;
    private TextView mAddView;
    private LinearLayout mChooseLayout;
    String name = "abc";


    public ChatBottomLayout(Context context) {
        this(context , null);
    }

    public ChatBottomLayout(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public ChatBottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_chat , this , true);
        init(view);
    }

    private void init(View view) {
        mAddView = (TextView) findViewById(R.id.add_view);
        mContentText = (EditText) findViewById(R.id.edittext);
        mChooseLayout = (LinearLayout) findViewById(R.id.choose_layout);


        mAddView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mAddView) {
            if (mChooseLayout.getVisibility() == GONE) {
                mChooseLayout.setVisibility(VISIBLE);
            }else if (mChooseLayout.getVisibility() == VISIBLE){
                mChooseLayout.setVisibility(GONE);
                mContentText.requestFocus();
            }
        }
    }
}
