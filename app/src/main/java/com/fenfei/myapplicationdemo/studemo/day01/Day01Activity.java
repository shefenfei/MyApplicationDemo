package com.fenfei.myapplicationdemo.studemo.day01;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.ui.TakePhotoActivity;
import com.fenfei.myapplicationdemo.ui.TestActivity;

import java.util.Arrays;
import java.util.List;

/**
 * day01 Intent的学习
 */
public class Day01Activity extends AppCompatActivity {

    String TAG = "Day01Activity";

    @Override
    @TargetApi(24)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day01);

        //处理隐匿意图的Intent
//        getIntent().hasExtra(MediaStore.EXTRA_OUTPUT);

        List<String> list1 = Arrays.asList("a", "b", "v", "c", "a", "d" , "12" ,"232" ,"asd");
        List<String> list2 = Arrays.asList("b", "d", "a", "e", "o" , "asd" ,"c");

        Button button = (Button) findViewById(R.id.test);
        button.setOnClickListener(view -> {
//            testCalander();
//            capturePhoto();
            Utils.resultSet(list1 , list2 , false).parallelStream().forEach(s -> Log.e(TAG, "onCreate: " +s ));
        });

    }

    private void testIntent() {
        ComponentName componentName = new ComponentName(this, TakePhotoActivity.class);
        Intent sendIntent = new Intent(this, TestActivity.class);

        sendIntent.setClassName("com.fenfei.myapplicationdemo.ui", "TestActivity");
        sendIntent.setClassName(this, "TestActivity");
        sendIntent.setComponent(componentName);
        sendIntent.setClass(this, TestActivity.class);

        //查看照片或查看地图应用的地址
        sendIntent.setAction(Intent.ACTION_VIEW);
        //社交应用或电子邮件
        sendIntent.setAction(Intent.ACTION_SEND);

        //自定义Action请使用软件名做前缀
        String ACTION_TIMETERVEL = "com.fenfei.action.TIMEVIEW";

        Uri uri = Uri.parse("");
        //设置数据Uri
        sendIntent.setData(uri);
        //设置MIME 类型
        sendIntent.setType("");
        //同时显示的设置二者, 若要同时设置 URI 和 MIME 类型，请勿调用 setData() 和 setType()，因为它们会互相抵消彼此的值。请始终使用 setDataAndType() 同时设置 URI 和 MIME 类型。
        sendIntent.setDataAndType(uri, "text/html");


        //携带数据 ，可携带其实类型数据一起打包发出
        Bundle bundle = new Bundle();
        bundle.putString("haha", "hello world");
        sendIntent.putExtra("data", bundle);


        Bundle b = getIntent().hasExtra("data") ? getIntent().getBundleExtra("data") : null;

        sendIntent.putExtra(Intent.EXTRA_EMAIL, ""); //收件人
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, ""); //主题


        String EXTRA_GET_PHONE_NUMBER = "com.fefei.pets.EXTRA_GET_PHONE_NUMBER";
        sendIntent.putExtra(EXTRA_GET_PHONE_NUMBER, bundle);

        //强制使用应用选择器
        String title = getResources().getString(R.string.app_name);
        Intent chooser = Intent.createChooser(sendIntent, title);

        //如果没有activity能处理这个隐匿的意图，则会报错
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(this, "没有可用的应用", Toast.LENGTH_SHORT).show();
        }

    }


    private void testPendingIntent() {
        Intent intent = new Intent();
        int REQUEST_CODE = 10000;
        //创建启动activity的
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE);

        //创建启动Service的
        PendingIntent serviceIntent = PendingIntent.getService(this, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE);

        //创建启动广播的
        PendingIntent broadCastIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE);


    }


    //闹钟的Intent
    private void testAlarmIntent(String message, int hour, int minutes) {
        Intent intent = new Intent();
        intent.setAction(AlarmClock.ACTION_SET_ALARM);

        Bundle bundle = new Bundle();
        bundle.putInt(AlarmClock.EXTRA_HOUR, 10);
        bundle.putInt(AlarmClock.EXTRA_MINUTES, 20);
        bundle.putString(AlarmClock.EXTRA_MESSAGE, "我的闹钟");

        //或使用以下方式

        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);

        if (alarmIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(alarmIntent);
        }

    }


    private void testSetAlarmTimer(String msg, long length) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                    .putExtra(AlarmClock.EXTRA_LENGTH, length)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, msg);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else {
            Log.e(TAG, "testSetAlarmTimer: 当前的系统版本过低！！！");
        }

    }


    //添加日历事件

    /**
     * <activity ...>
     * <intent-filter>
     * <action android:name="android.intent.action.INSERT" />
     * <data android:mimeType="vnd.android.cursor.dir/event" />
     * <category android:name="android.intent.category.DEFAULT" />
     * </intent-filter>
     * </activity>
     */
    private void testCalander() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setDataAndType(CalendarContract.Events.CONTENT_URI, "vnd.android.cursor.dir/event");
        intent.putExtra(CalendarContract.Events.ALL_DAY, true);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1000);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 2000);
        intent.putExtra(CalendarContract.Events.TITLE, "我的日历事件");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "日历事件的描述");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "上海");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_SELECT_PHONE_NUMBER = 2;

    //拍照片的Intent
    private void testTakePicture(Uri baseUri, String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.withAppendedPath(baseUri, fileName));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmap);
        }

        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
            if (uri != null) {
                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(numberIndex);
                    Log.e(TAG, "onActivityResult: " + number);
                }
            }


        }


    }


    //静态图像模式启动相机
    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @TargetApi(19)
    private void testActionGetContent() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("");
    }






}
