package com.fenfei.myapplicationdemo.fragments;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.ui.FragmentTestActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {

    FragmentTestActivity mFragmentTestActivity_;

    Activity mActivity_;


    TextView tv;
    public AFragment() {
        // Required empty public constructor
    }

    public static AFragment newInstance() {
        return new AFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentTestActivity) {
            mFragmentTestActivity_ = (FragmentTestActivity) context;
            mFragmentTestActivity_.mRequestManager_.load(Uri.parse("")).into(new ImageView(context));
        }
        mActivity_ = mFragmentTestActivity_;

        new EditText(getContext()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        mActivity_.apiService.xxxx();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = (TextView)view.findViewById(R.id.fragment_a_text);
    }

    public void changeText(String text) {
        tv.setText(text);
    }
}
