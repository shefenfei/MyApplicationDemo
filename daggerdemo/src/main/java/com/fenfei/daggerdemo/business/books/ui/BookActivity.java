package com.fenfei.daggerdemo.business.books.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fenfei.daggerdemo.R;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.book_container , new BookFragment()).commitAllowingStateLoss();
    }
}
