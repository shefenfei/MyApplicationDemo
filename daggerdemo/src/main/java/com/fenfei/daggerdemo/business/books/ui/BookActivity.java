package com.fenfei.daggerdemo.business.books.ui;

import android.os.Bundle;

import com.fenfei.daggerdemo.R;
import com.fenfei.daggerdemo.base.BaseActivity;

public class BookActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.book_container , new BookFragment()).commitAllowingStateLoss();
    }
}
