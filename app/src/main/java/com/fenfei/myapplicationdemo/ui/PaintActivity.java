package com.fenfei.myapplicationdemo.ui;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.fenfei.myapplicationdemo.R;

/**
 * 画笔(Paint) ,画布(Canvas) , 矩阵(Matrix)
 */
public class PaintActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private ColorMatrix mColorMatrix;

    //红 ，绿 ，蓝，透明
    private ColorFilter mRedColorFilter;
    private ColorFilter mGreenColorFilter;
    private ColorFilter mBlueColorFilter;
    private ColorFilter mAlphaColorFilter;

    private ImageView mImageView;

    private SeekBar mRedSeekBar, mGreenSeekBar, mBlueSeekBar;
    private float redSeek  , greenSeek, blueSeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        mRedColorFilter = new ColorFilter();
        mGreenColorFilter = new ColorFilter();
        mBlueColorFilter = new ColorFilter();
        mAlphaColorFilter = new ColorFilter();

        mImageView = (ImageView) findViewById(R.id.imageview);

        mRedSeekBar = (SeekBar) findViewById(R.id.red_seekbar);
        mGreenSeekBar = (SeekBar) findViewById(R.id.green_seekbar);
        mBlueSeekBar = (SeekBar) findViewById(R.id.blue_seekbar);

        mRedSeekBar.setOnSeekBarChangeListener(this);
        mGreenSeekBar.setOnSeekBarChangeListener(this);
        mBlueSeekBar.setOnSeekBarChangeListener(this);

    }

    /**
     *
     *
     *      颜色矩阵 4*5 的矩阵 ，
             / 0, 0, 0, 0, 0, \  ----> R (红)   第五列为颜色分量矩阵，控制显示效果
            |  0, 0, 0, 0, 0,  |----> G (绿)
            |  0, 0, 0, 0, 0,  |----> B (蓝)
             \ 0, 0, 0, 0, 0,  /----> A (透明)


     * @param red
     * @param green
     * @param blue
     * @param alpha
     */
    private void setArgb(float red, float green, float blue, float alpha) {
        mColorMatrix = new ColorMatrix(new float[]{
                red, 0,     0,    0,     0,
                0,   green, 0,    0,     0,
                0,   0,     blue, 0,     0,
                0,   0,     0,    alpha, 0,
        });
        mImageView.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        float fileter = (float) progress / 100;
        if (mRedSeekBar == seekBar) {
            redSeek = fileter;
        }
        if (mGreenSeekBar == seekBar) {
            greenSeek = fileter;
        }
        if (mBlueSeekBar == seekBar) {
            blueSeek = fileter;
        }
        setArgb(redSeek , greenSeek , blueSeek , 1);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
