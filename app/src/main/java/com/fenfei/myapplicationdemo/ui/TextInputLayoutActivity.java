package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fenfei.myapplicationdemo.R;

public class TextInputLayoutActivity extends AppCompatActivity {

    private TextInputLayout mTextInputLayout_;
    private LinearLayout mLinearLayout_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        mTextInputLayout_ = (TextInputLayout) findViewById(R.id.text_input_layout);
        mLinearLayout_ = (LinearLayout) findViewById(R.id.lin_layout);
        mTextInputLayout_.setErrorEnabled(true);

        mTextInputLayout_.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = mTextInputLayout_.getEditText()
                        .getText().toString().trim();
                if (value.length() > 10) {
                    mTextInputLayout_.setError("超过了....");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        measureChildView();
    }

    private void measureChildView() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLinearLayout_.getLayoutParams();
        params.width = 1000;
        mLinearLayout_.setLayoutParams(params);
    }
}
