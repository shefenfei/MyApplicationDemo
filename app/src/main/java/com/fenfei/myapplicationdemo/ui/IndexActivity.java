package com.fenfei.myapplicationdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.fenfei.myapplicationdemo.R;

import java.io.File;

public class IndexActivity extends AppCompatActivity {

    private ListView mListView_;
    // The path to the root of this app's internal storage
    private File mPrivateRootDir;
    // The path to the "images" subdirectory
    private File mImagesDir;
    // Array of files in the images subdirectory
    File[] mImageFiles;
    // Array of filenames corresponding to mImageFiles
    String[] mImageFilenames;

    private Intent mResultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        mResultIntent = new Intent("com.fenfei.myapplicationdemo.ACTION_RETURN_FILE");
        mPrivateRootDir = getFilesDir();
        mImagesDir = new File(mPrivateRootDir , "images");
        mImageFiles = mImagesDir.listFiles();
        setResult(Activity.RESULT_CANCELED, null);
    }
}
