package com.fenfei.myapplicationdemo.ui;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {

    private Button mButton_l;
    private Button mButton_read;
    private TextView mTextView_content;

    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + "/MyDemoDir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        mButton_l = (Button) findViewById(R.id.button);
        mButton_read = (Button) findViewById(R.id.readbutton);
        mTextView_content = (TextView) findViewById(R.id.content);

        mButton_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });

        mButton_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile(dirPath + File.separator +"test.txt");
            }
        });
    }

    private void createFile() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) { //兼容6.0的动态权限
            requestPermission();
        } else {
            create();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            create();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void create() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path + File.separator + "test.txt");
        if (!file.exists()) {
            try {
                boolean isSuccess = file.createNewFile();
                Log.e("File", "createFile " + isSuccess);
                if (isSuccess) {
                    writeFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            writeFile();
        }
    }

    private void writeFile() {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String path = file.getAbsolutePath();
        Log.e("File", "writeFile: " + path);
        File f = new File(path, "test.txt");
        f.deleteOnExit();
        FileWriter fos = null;
        try {
            fos = new FileWriter(f);
            String content = "abvasdasdasd1233";
            fos.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFile(String path) {
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            int len = 0;
            byte[] buffer = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while ((len = fis.read(buffer))!=-1) {
                sb.append(new String(buffer , 0 , len));
                Log.e("Content", "writeFile: " + new String(buffer , 0 , len) );
            }
            mTextView_content.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}