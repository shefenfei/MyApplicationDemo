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
public class Fragment_2 extends Fragment {


    public Fragment_2() {
        // Required empty public constructor
    }

    public static Fragment_2 newInstance() {
        return new Fragment_2();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_2, container, false);
    }

}
