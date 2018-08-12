package com.fenfei.criminalintent.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fenfei.criminalintent.R;
import com.fenfei.criminalintent.fragments.CrimeFragment;
import com.fenfei.criminalintent.pojos.Crime;
import com.fenfei.criminalintent.pojos.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = findViewById(R.id.crime_viewpager);
        mCrimes = CrimeLab.getCrimeLab(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(uuid)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

    public static Intent newIntent(Context packageContext, UUID uuid) {
        Intent intent = new Intent(packageContext , CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID , uuid);
        return intent;
    }
}
