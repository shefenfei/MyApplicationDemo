package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.views.ScrollAnimationList;

public class TestScrollActivity extends AppCompatActivity {

    private ScrollAnimationList mAnimationList_;
    private Button mButton_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll);

        mAnimationList_ = (ScrollAnimationList) this.findViewById(R.id.scroll_view);
        mButton_ = (Button) this.findViewById(R.id.scr);


        mButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scrollBy();
            }
        });
    }

    private void scrollBy() {
        mAnimationList_.getChildAt(0).scrollBy(0 , 10);
    }
}
