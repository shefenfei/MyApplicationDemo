package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.fragments.Fragment_1;
import com.fenfei.myapplicationdemo.fragments.Fragment_2;

/**
 * 测试fragment返回栈
 */
public class BackStackActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager_;

    FrameLayout frameLayout;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_stack);


        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            mFragmentManager_ = getSupportFragmentManager();
            fragmentTransaction = mFragmentManager_.beginTransaction();
            frameLayout = (FrameLayout) findViewById(R.id.container);
            fragmentTransaction.add(R.id.container, new Fragment_1()).addToBackStack(null).commit();
        }


        Button button = (Button) findViewById(R.id.replace);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = mFragmentManager_.beginTransaction().add(R.id.container, new Fragment_2());
                fragmentTransaction.addToBackStack(null);
                ft.commit();
            }
        });

    }

}
