package com.fenfei.myapplicationdemo.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.viewmodels.SharedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class NewsListFragment extends Fragment {

    private ListView mListView_;
    private SharedViewModel mSharedViewModel;

    private String[] datasets = {"新闻1", "新闻2", "新闻3", "新闻4", "新闻5", "新闻6", "新闻7", "新闻8", "新闻9", "新闻10"};

    public NewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        mListView_ = view.findViewById(R.id.news_listview);
        mListView_.setAdapter(new ArrayAdapter<String>(view.getContext() ,
                android.R.layout.simple_list_item_1 ,
                datasets));
        mListView_.setOnItemClickListener((parent, view1, position, id) -> {
           mSharedViewModel.select(datasets[position]);
        });
    }

}
