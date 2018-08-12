package com.fenfei.criminalintent.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.fenfei.criminalintent.R;
import com.fenfei.criminalintent.pojos.Crime;
import com.fenfei.criminalintent.pojos.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final int REQUEST_DATE = 0;
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";


    private Crime mCrime;

    private EditText mEditText;
    private Button mDateButton;
    private CheckBox mCheckBox;

    public CrimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();

        UUID uuid = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.getCrimeLab(getActivity()).getCrimeById(uuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mDateButton = view.findViewById(R.id.crime_date);
        mCheckBox = view.findViewById(R.id.crime_solved);

        String date = SimpleDateFormat.getDateTimeInstance().format(mCrime.getDate());
        mDateButton.setText(date);
        mDateButton.setEnabled(mCrime.isSolved());
        mDateButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();
            DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCrime.getDate());
            datePickerFragment.setTargetFragment(CrimeFragment.this , REQUEST_DATE);
            datePickerFragment.show(fragmentManager , "adb");
        });

        mCheckBox.setOnCheckedChangeListener((v , checked) -> {
            mDateButton.setEnabled(checked);
            mCrime.setSolved(checked);
        });

        mEditText = view.findViewById(R.id.crime_title);
        mEditText.setText(mCrime.getTitle());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(EXTRA_DATE);
            mCrime.setDate(date);
            mDateButton.setText(mCrime.getDate().toString());
        }
    }

    //fragment之间传值不要直接使用getIntent() 依赖性太强
    public static CrimeFragment newInstance(UUID uuid) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID , uuid);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        intent.putExtra("name" , "from activity");
        getActivity().setResult(Activity.RESULT_OK, intent);
    }
}
