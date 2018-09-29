package com.fenfei.myapplicationdemo.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fenfei.myapplicationdemo.R;

import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class QrCodeActivity extends AppCompatActivity {

    private ZXingView mZXingView_;
    private Button mButton_;

    private int REQUEST_CAMERA_CODE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        requestPermission();
        mZXingView_ = (ZXingView) findViewById(R.id.zxingview);
        mButton_ = (Button) findViewById(R.id.scan);
        mButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mZXingView_.startCamera();
                mZXingView_.showScanRect();
            }
        });
    }

    private void requestPermission() {
        int permissonStatus = ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA);
        if (permissonStatus == PackageManager.PERMISSION_DENIED) {
            Log.e("TAG", "requestPermisson: 无权访问日历" );
            if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.CAMERA)) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("调用相机权限");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(QrCodeActivity.this ,
                                new String[]{Manifest.permission.CAMERA} ,REQUEST_CAMERA_CODE );
                    }
                }).show();
            }else {
                ActivityCompat.requestPermissions(QrCodeActivity.this ,
                        new String[]{Manifest.permission.CAMERA} ,REQUEST_CAMERA_CODE );
            }
        }else {
            Toast.makeText(this , "调用相机权限已申请" , Toast.LENGTH_SHORT).show();
        }
        Log.e("TAG", "requestPermisson: " + permissonStatus );

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CAMERA_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this , "相机权限已申请" , Toast.LENGTH_SHORT).show();
            }else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.CAMERA)) {
                    Toast.makeText(this , "申请相机权限被拒绝" , Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

}
