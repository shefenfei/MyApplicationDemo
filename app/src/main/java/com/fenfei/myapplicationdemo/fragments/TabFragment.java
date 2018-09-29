package com.fenfei.myapplicationdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fenfei.myapplicationdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    public TabFragment() {
        // Required empty public constructor
    }

    public static TabFragment newInstance() {
        return new TabFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }



}
