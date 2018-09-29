package com.fenfei.myapplicationdemo.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.viewmodels.SharedViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment {


    private TextView mTextView_;
    private ImageView mImageView;

    private SharedViewModel mSharedViewModel;

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mTextView_ = view.findViewById(R.id.content_news);
        mImageView = view.findViewById(R.id.iv_day05);
        mSharedViewModel.getSelected().observe(getActivity(), s -> {
            mTextView_.setText(s);
        });

        Glide.with(getContext())
                .load(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515129437076&di=8e84feba21a08c3d16fa9c92177734d7&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F140613%2F240466-1406130QP479.jpg"))
                .into(mImageView);
    }
}
