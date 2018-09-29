package com.fenfei.myapplicationdemo.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.studemo.day08.StockLiveData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.fenfei.myapplicationdemo.R.id.imageview;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button mButton_;
    private ImageView mImageView_;

    public BFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButton_ = (Button) view.findViewById(R.id.button_b);
        mImageView_ = (ImageView) view.findViewById(imageview);
        mButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseBitmap("http://pic51.nipic.com/file/20141027/11284670_094822707000_2.jpg", getContext(), mImageView_);
                mListener.onFragmentInteraction("change from B");
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StockLiveData.get(getActivity().getPackageName()).observe(this , price -> {
            //update UI
        });
    }

    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewsItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String text);
    }


    /**
     * 处理bitmap
     *
     * @param url
     * @param context
     */
    public void parseBitmap(String url, Context context, final ImageView imageview) {
        Uri uri = Uri.parse(url);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        BaseBitmapDataSubscriber dataSubscriber = new BaseBitmapDataSubscriber() {

            @Override
            protected void onNewResultImpl(Bitmap bitmap) {
                imageview.setImageBitmap(bitmap);
                //这里处理bitmap
                byte[] bytes = getBitmapBytes(bitmap, true);
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                String errorLog = dataSource.getFailureCause().getMessage();
                Log.e("加载错误", "onFailureImpl: " + errorLog);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(1);
                    }
                });
            }
        };
        dataSource.subscribe(dataSubscriber, CallerThreadExecutor.getInstance());
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Bitmap b;
                try {
                    b = BitmapFactory.decodeStream(getResources().getAssets().open("test.jpg"));
                    mImageView_.setImageBitmap(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    };


    //需要对图片进行处理，否则微信会在log中输出thumbData检查错误
    private static byte[] getBitmapBytes(Bitmap bitmap, boolean paramBoolean) {
        Bitmap localBitmap = Bitmap.createBitmap(80, 80, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);
        int i;
        int j;
        if (bitmap.getHeight() > bitmap.getWidth()) {
            i = bitmap.getWidth();
            j = bitmap.getWidth();
        } else {
            i = bitmap.getHeight();
            j = bitmap.getHeight();
        }
        while (true) {
            localCanvas.drawBitmap(bitmap, new Rect(0, 0, i, j), new Rect(0, 0, 80, 80), null);
            if (paramBoolean) bitmap.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
//                F.out(e);
            }
            i = bitmap.getHeight();
            j = bitmap.getHeight();
        }
    }

    class WXMediaMessage {
        byte[] thumbData;
    }

}
