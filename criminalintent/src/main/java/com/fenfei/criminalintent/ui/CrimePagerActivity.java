package com.fenfei.criminalintent.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fenfei.criminalintent.R;
import com.fenfei.criminalintent.fragments.CrimeFragment;
import com.fenfei.criminalintent.pojos.Crime;
import com.fenfei.criminalintent.pojos.CrimeLab;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

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



















//        playview
//        com.google.android.exoplayer2.ExoPlayer exoPlayer;

//        ExoPlayer exoPlayer1 = ExoPlayerFactory.newSimpleInstance();

        Handler handler = new Handler() ;
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectorFatory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(videoTrackSelectorFatory);
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(this , defaultTrackSelector);

        MediaSource chileMediaSource = new MergingMediaSource();
        MediaSource mediaSource = new LoopingMediaSource(chileMediaSource , 10);
        mediaSource.prepareSource(exoPlayer, true, new MediaSource.SourceInfoRefreshListener() {
            @Override
            public void onSourceInfoRefreshed(MediaSource source, Timeline timeline, @Nullable Object manifest) {

            }
        });
        SimpleExoPlayerView simpleExoPlayerView = new SimpleExoPlayerView(this);
        simpleExoPlayerView.setPlayer(exoPlayer);

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
