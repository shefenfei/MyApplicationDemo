package com.fenfei.criminalintent.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.fenfei.criminalintent.fragments.CrimeListFragment;

public class CrimeListActivity extends SimpleFragmentActivity {

    Fragment mFragment;

    @Override
    protected Fragment createFragment() {
        int x = 0b101010;
        long phone = 130_4069_1917L;
        mFragment = new CrimeListFragment();
        return mFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onActivityResult(requestCode , resultCode , data);
    }
}
