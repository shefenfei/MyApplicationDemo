package com.fenfei.myapplicationdemo.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fenfei.myapplicationdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//7.0拍照
public class TakePhotoActivity extends AppCompatActivity {

    private Button mButton_;
    private ImageView mImageView_;

    private int REQUEST_TAKE_PHOTO = 1001;

    Uri uri;

    File imgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        mImageView_ = (ImageView) findViewById(R.id.imageview_user);
    }


    public void takePhoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) { //7.0
//            buildTempFile(intent);
            photo();
        } else {
            buildTempFile(intent);
        }
    }

    private void buildTempFile(Intent intent) {
        File dir = new File(getFilesDir(), "images");
        File file = new File(dir, "myPhoto.jpg");
        if (dir.exists()) {
            dir.deleteOnExit();
        }
        dir.mkdirs();
        try {
            boolean isCreated = file.createNewFile();
            Log.e("status", "buildTempFile: " + isCreated);
        } catch (IOException e) {
            e.printStackTrace();
        }
        uri = FileProvider.getUriForFile(this, "com.shefenfei.fileprovider", file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }


    private void photo() {
//        imgFile = new File(getExternalCacheDir(), "capture.jpg");
        imgFile = new File(Environment.getExternalStorageDirectory(), "capture.jpg");

        if (imgFile.exists()) {
            imgFile.delete();
        }
        try {
            imgFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (Build.VERSION.SDK_INT >= 24) {
//            uri = FileProvider.getUriForFile(this, "com.media.file", imgFile);
//        } else {
        uri = Uri.fromFile(imgFile);
//        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);// 向多媒体数据库存入拍照后的照片路径
            startActivityForResult(intent, 1);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    mImageView_.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if (requestCode == 1) {
                if (imgFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getPath());
                    mImageView_.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
