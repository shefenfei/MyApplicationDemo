package com.fenfei.criminalintent.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.fenfei.criminalintent.fragments.CrimeFragment;

import java.util.UUID;

public class MainActivity extends SimpleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "crime_id";

    @Override
    protected Fragment createFragment() {
        //加入了新的代码
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(uuid);
    }


    public static Intent newIntent(Context context , UUID uuid) {
        Intent intent = new Intent(context , MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID , uuid);
        return intent;
    }
}
