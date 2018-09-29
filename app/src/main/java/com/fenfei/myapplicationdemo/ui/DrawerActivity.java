package com.fenfei.myapplicationdemo.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.views.VerticalViewPager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = "DrawerActivity";
    VerticalViewPager viewPager;
    int UPTATE_VIEWPAGER = 0;

    int index = 0;
    Toolbar toolbar;

    private ShareActionProvider mShareActionProvider_;
    private Object mActivityInfo_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showSnakeBar(view);
//                sendMail();
//                sendMail1();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initView();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        test1();
        getActivityInfo();
    }

    private void sendMail1() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setType("text/plain");
        handleShareIntent(sendIntent);
    }

    private void sendMail() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String title = "邮件";
        Intent i = Intent.createChooser(intent , title);
        if (i.resolveActivity(getPackageManager())!=null) {
            startActivity(i);
        }
    }

    private void showSnakeBar(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void testFile() {
        createExternalStoragePrivateFile();
        boolean has = hasExternalStoragePrivatePicture();
        Log.e("file", "testFile: " + has);
    }

    private void test1() {
        File file = new File(getFilesDir(), "test.jpg");
        Log.e(TAG, "testSmileRequest: " + file.getAbsolutePath() );

        String url = "http://www.baidu.com/test.png";
        String name = Uri.parse(url).getLastPathSegment();
        Log.e(TAG, "testSmileRequest: " + name );


        int status = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR);
        if (status == PackageManager.PERMISSION_GRANTED) {
            // TODO: 2016/12/27 已经有操作权限
        }


    }


    void createExternalStoragePrivateFile() {
        // Create a path where we will place our private file on external
        // storage.
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "DemoFile.jpg");
        Log.e("path", "createExternalStoragePrivateFile: " + file.getAbsolutePath());

        try {
            // Very simple code to copy a picture from the application's
            // resource into the external file.  Note that this code does
            // no error checking, and assumes the picture is small (does not
            // try to copy it in chunks).  Note that if external storage is
            // not currently mounted this will silently fail.
            InputStream is = getResources().openRawResource(R.drawable.ic_menu_share);
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    boolean hasExternalStoragePrivatePicture() {
        // Create a path where we will place our picture in the user's
        // public pictures directory and check if the file exists.  If
        // external storage is not currently mounted this will think the
        // picture doesn't exist.
        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (path != null) {
            File file = new File(path, "DemoFile.jpg");
            return file.exists();
        }
        return false;
    }

    private void initView() {
        final int[] icons = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3};

        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(icons[0]);
//        iv1.setRotation(-90f);

        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(icons[1]);
//        iv2.setRotation(-90f);

        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(icons[2]);
//        iv3.setRotation(-90f);

        final ImageView[] views = {
                iv1, iv2, iv3
        };


        viewPager = (VerticalViewPager) findViewById(R.id.viewpager);
//        viewPager.setOrientation(DirectionalViewPager.VERTICAL);
//        viewPager.setPageTransformer(true , new DepthPageTransformer());
//        viewPager.setAdapter(new TestFragmentAdapter(getSupportFragmentManager()));


        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return icons.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views[position]);
                return views[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views[position]);
            }
        });


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPTATE_VIEWPAGER;
                if (index == icons.length) {
                    index = 0;
                }
                message.arg1 = index++;
                mHandler.sendMessage(message);
            }
        }, 5000, 3000);
    }


    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPTATE_VIEWPAGER) {
                if (msg.arg1 != 0) {
                    viewPager.setCurrentItem(msg.arg1, true);
                } else {
                    //false 当从末页调到首页是，不显示翻页动画效果，
                    viewPager.setCurrentItem(msg.arg1, false);
                }
            }
        }
    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);
        mShareActionProvider_ = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return true;
    }


    private void handleShareIntent(Intent intent) {
        if (mShareActionProvider_!=null) {
            mShareActionProvider_.setShareIntent(intent);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long_image
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            getSharedPreferences("shared", MODE_PRIVATE | MODE_APPEND);
            getPreferences(MODE_PRIVATE);
            return true;
        }

        if (id == R.id.menu_item_share) {
            sendMail1();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void requestPermission() {
//        ActivityCompat.requestPermissions(this , );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    public void getActivityInfo() {
        try {
            ActivityInfo activityInfo = this.getPackageManager().getActivityInfo(
                    this.getComponentName() , PackageManager.GET_META_DATA);

            String name = activityInfo.name;
            Log.e(TAG, "getActivityInfo: " + name );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
