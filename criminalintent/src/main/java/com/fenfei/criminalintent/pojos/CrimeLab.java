package com.fenfei.criminalintent.pojos;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by shefenfei on 2018/3/26.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    private CrimeLab() {
        mCrimes = new ArrayList<>();
    }

    public void addCrime(Crime crime) {
        getCrimes().add(crime);
    }

    public static CrimeLab getCrimeLab(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab();
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrimeById(UUID uuid) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }
}
