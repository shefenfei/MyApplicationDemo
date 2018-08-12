package com.fenfei.criminalintent.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.fenfei.criminalintent.fragments.CrimeListFragment;

public class CrimeListActivity extends SimpleFragmentActivity {

    Fragment mFragment;

    @Override
    protected Fragment createFragment() {
        mFragment = new CrimeListFragment();
        return mFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onActivityResult(requestCode , resultCode , data);
    }
}
