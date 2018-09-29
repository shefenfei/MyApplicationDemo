package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.fenfei.myapplicationdemo.R;

import org.xml.sax.XMLReader;

public class DetailActivity extends AppCompatActivity {

    ImageView mImageView_;
    RequestManager rm;

    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String url = getIntent().getStringExtra("url");
        mImageView_ = (ImageView) findViewById(R.id.detail);

        rm = Glide.with(this);
        rm.load(Uri.parse(url)).into(mImageView_);


        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                return null;
            }
        };
        Html.fromHtml("", 1, imageGetter, new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy: ");
        if (!rm.isPaused()) {
            rm.pauseRequests();
        }
    }
}
