package com.fenfei.criminalintent.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.fenfei.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";
    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;

    public DatePickerFragment() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date , null);

        mDatePicker = view.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, month, day, (view1, year1, monthOfYear, dayOfMonth) -> {
            int y = mDatePicker.getYear();
            int m = mDatePicker.getMonth();
            int d = mDatePicker.getDayOfMonth();

            Date date1 = new GregorianCalendar(y , m , d).getTime();
            setResult(Activity.RESULT_OK , date1);
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setView(view)
                .setPositiveButton(android.R.string.ok , null)
                .create();
    }


    //关键方法
    private void setResult(int resultCode , Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE , date);
        getTargetFragment().onActivityResult(getTargetRequestCode() , resultCode , intent);
    }


    public static DatePickerFragment newInstance(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DATE , date);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        return datePickerFragment;
    }

}
