package com.fenfei.criminalintent.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.fenfei.criminalintent.R;
import com.fenfei.criminalintent.pojos.Crime;
import com.fenfei.criminalintent.pojos.CrimeLab;
import com.fenfei.criminalintent.ui.CrimePagerActivity;
import com.fenfei.criminalintent.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CrimeAdapter mCrimeAdapter;

    public CrimeListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fragmentManager 管理 是否要显示optionMenu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity()); // 后续可以用viemodel代替
        List<Crime> crimes = crimeLab.getCrimes();
        if (mCrimeAdapter == null) {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(mCrimeAdapter);
        }else {
            mCrimeAdapter.notifyDataSetChanged();
//            mCrimeAdapter.notifyItemChanged(0);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Crime mCrime;

        public TextView mTitle;
        public TextView mDate;
        public CheckBox mCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitle = itemView.findViewById(R.id.crime_title);
            mDate = itemView.findViewById(R.id.crime_date);
            mCheckBox = itemView.findViewById(R.id.checkBox);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitle.setText(crime.getTitle());
            mDate.setText(SimpleDateFormat.getDateTimeInstance().format(crime.getDate()));
            mCheckBox.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View v) {
//            jumpActivity();
//            fragment2activity();
            gotoCrimeViewPagerActivity();
        }

        private void jumpActivity() {
            Toast.makeText(getActivity() ,
                    mCrime.getTitle() + "clicked!" , Toast.LENGTH_SHORT).show();
            Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }

        private void fragment2activity() {
            Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            startActivityForResult(intent , 10001);
        }

        private void gotoCrimeViewPagerActivity() {
            Intent intent = CrimePagerActivity.newIntent(getActivity() , mCrime.getId());
            startActivity(intent);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001) {
            Log.e("CrimeListFragment", "onActivityResult: " +  resultCode);
//            String name = data.getStringExtra("name");
//            Log.e("CrimeListFragment", "onActivityResult: " + name );
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragement_menu_list , menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime() ;
                CrimeLab.getCrimeLab(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                break;

            case R.id.menu_item_show_subtitle:
                updateSubTitle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateSubTitle() {
        int count = CrimeLab.getCrimeLab(getActivity()).getCrimes().size();
        String subTitle = String.valueOf(count);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subTitle);
    }
}
