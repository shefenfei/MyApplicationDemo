package com.fenfei.myapplicationdemo.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.broadcasts.MyReceiver;
import com.fenfei.myapplicationdemo.interfaces.Contants;
import com.fenfei.myapplicationdemo.services.MyIntentService;
import com.fenfei.myapplicationdemo.views.PullToRefreshListView;

public class IntentServiceActivity extends AppCompatActivity {


    PullToRefreshListView mPulltoRefreshView;
    Button mButton_;

    MyReceiver myReceiver = new MyReceiver() {

        @Override
        public void getStr(String str) {
            Log.e("hi", "getStr: " + str );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        mButton_ = (Button) this.findViewById(R.id.click);
        mButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentServiceActivity.this , MyIntentService.class);
                startService(intent);
            }
        });


        mPulltoRefreshView = (PullToRefreshListView) this.findViewById(R.id.listview);
        mPulltoRefreshView.setAdapter(new ArrayAdapter<String>(
                this ,
                android.R.layout.simple_list_item_1,
                new String[]{"1", "2" ,"3" ,"4" ,"5" ,"6" ,"7" ,"8"
        }));

        TaskStackBuilder task = TaskStackBuilder.create(this);
//        task.addNextIntent()

        IntentFilter filter = new IntentFilter(Contants.BROAD_CAST);

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver , filter);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("IntentServiceActivity", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
    }
}
