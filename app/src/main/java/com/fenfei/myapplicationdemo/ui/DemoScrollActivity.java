package com.fenfei.myapplicationdemo.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.utils.DensityUtils;

/**
 * Created by shefenfei on 2018/3/15.
 */

public class DemoScrollActivity extends AppCompatActivity {

    boolean isOpen = false;

    @TargetApi(21)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

//        testLayoutAnimation();

        testViewAnimation();
//        ViewStub viewStub ;
       /*
        Bitmap b = Bitmap.createBitmap(null);
        Palette p = Palette.from(b).generate();

        Palette.from(b).generate(palette -> {
            palette.getDarkVibrantColor(Color.BLACK);
        });
        */

        ImageView imageView = (ImageView) findViewById(R.id.iv_demo);
        ViewOutlineProvider provider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0 , 0, 100 , 100);
            }
        };

        imageView.setClipToOutline(true);
        imageView.setOutlineProvider(provider);
    }

    private void testViewAnimation() {
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.ll);

        ImageView iv1 = (ImageView) findViewById(R.id.iv_1);
        ImageView iv2 = (ImageView) findViewById(R.id.iv_2);
        ImageView iv3 = (ImageView) findViewById(R.id.iv_3);
        ImageView iv4 = (ImageView) findViewById(R.id.iv_4);
        ImageView iv5 = (ImageView) findViewById(R.id.iv_5);


        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv1, "alpha", 1F, 0.5F);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv2, "translationX", 200f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv3, "translationY", 200F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(iv4, "translationX", -200F);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(iv5, "translationY", -200F);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.playTogether(animator1, animator2, animator3, animator4, animator5);


        ObjectAnimator animator11 = ObjectAnimator.ofFloat(iv1, "alpha", 0.5F, 1F);
        ObjectAnimator animator22 = ObjectAnimator.ofFloat(iv2, "translationX", 0);
        ObjectAnimator animator33 = ObjectAnimator.ofFloat(iv3, "translationY", 0);
        ObjectAnimator animator44 = ObjectAnimator.ofFloat(iv4, "translationX", 0);
        ObjectAnimator animator55 = ObjectAnimator.ofFloat(iv5, "translationY", 0);

        AnimatorSet animatorClose = new AnimatorSet();
        animatorClose.setDuration(2000);
        animatorClose.setInterpolator(new BounceInterpolator());
        animatorClose.playTogether(animator11, animator22, animator33, animator44, animator55);


        iv1.setOnClickListener(view -> {
            if (isOpen) {
                animatorClose.start();
                isOpen = false;
            } else {
                animatorSet.start();
                isOpen = true;
            }
        });
    }

    private void testLayoutAnimation() {
        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.ll);
        Button button = null;


        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(2000);
        LayoutAnimationController controller = new LayoutAnimationController(scaleAnimation, 0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(controller);

        button.setOnClickListener(vew -> {

            TextView textView = new TextView(DemoScrollActivity.this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText("新的View被添加了");

            linearLayout.addView(textView);
            linearLayout.setLayoutAnimation(controller);
        });


        float dp = DensityUtils.convertPixelsToDp(200);
        Log.e("DemoScrollActivity 1", "onCreate: " + dp);
        Log.e("DemoScrollActivity 2",
                "onCreate: " + DensityUtils.px2dip(this, 200));
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String BRAND = Build.BRAND;

        System.getProperty("os.version");


        PackageManager packageManager = getPackageManager();
        packageManager.getInstalledPackages(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);


        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }
}
