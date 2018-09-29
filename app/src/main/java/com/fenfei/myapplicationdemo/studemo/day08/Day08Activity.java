package com.fenfei.myapplicationdemo.studemo.day08;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.UserEntity;
import com.fenfei.myapplicationdemo.beans.UserListViewModel;

import java.util.List;

public class Day08Activity extends AppCompatActivity {

    private String TAG = "Day08Activity";

    private RecyclerView mRecyclerView;
    private UserEntityAdapter mEntityAdapter;

    private UserListViewModel mUserViewModel;

    private MutableLiveData<String> mCurrentData;

    private Button mFetchButton;
    private Button mAddButton;
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day08);

        mUserViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);

        mFetchButton = (Button) findViewById(R.id.fetch);
        mAddButton = (Button) findViewById(R.id.add_record);
        mTextView = (TextView) findViewById(R.id.content_viewmodel);


        mRecyclerView = (RecyclerView) findViewById(R.id.user_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEntityAdapter = new UserEntityAdapter();
        mRecyclerView.setAdapter(mEntityAdapter);

        Observer<PagedList<UserEntity>> observer = userEntities -> {
            mEntityAdapter.setList(userEntities);
        };

        mUserViewModel.loadUserByAge().observe(this, observer);

        mCurrentData = mUserViewModel.getData();
        mCurrentData.observe(this, s -> {
            Log.e(TAG, "onCreate: s =  " + s );
            mTextView.setText(s);
        });


        mFetchButton.setOnClickListener(v -> {
            mUserViewModel.getAddressInput().setValue("上海市区");
        });


        mAddButton.setOnClickListener(v -> {
            mCurrentData.setValue("值=" + count);
            count++;
        });


        StockLiveData.get("").observe(this , price -> {
            //update ui
            Log.e(TAG, "onCreate: 价格 =  " + price );
        });


        // map 则不需要
        LiveData<List<UserEntity>> userItems = mUserViewModel.getUserItems();
        LiveData<String> data = Transformations.map(userItems, item ->
                item.get(0).getUsername()
        );

        data.observe(this , s -> {
            Log.e(TAG, "onCreate: " + s );
        });


//        Transformations.switchMap() switchMap() 的返回值必须是livedata类型的
        LiveData<String> userIds = mUserViewModel.listAllUserIds();
        LiveData<UserEntity> map = Transformations.switchMap(userIds, this::chang2string);
        map.observe(this  , userEntity -> {

        });


        mUserViewModel.postCode.observe(this , s -> {
            Log.e(TAG, "onCreate: " + s );
        });
    }


    private LiveData<UserEntity> chang2string(String uid) {

        return null;
    }

    int count = 1;

    static class UserEntityAdapter extends PagedListAdapter<UserEntity, UserEntityAdapter.UserViewHolder> {

        protected UserEntityAdapter() {
            super(mDiffCallback);
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);

            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, int position) {
            UserEntity userEntity = getItem(position);
            holder.mUserId.setText(String.valueOf(userEntity.getUid()));
            holder.mUserName.setText(userEntity.getUsername());
        }


        public static final DiffCallback<UserEntity> mDiffCallback = new DiffCallback<UserEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull UserEntity oldItem, @NonNull UserEntity newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull UserEntity oldItem, @NonNull UserEntity newItem) {
                return oldItem == newItem;
            }
        };

        class UserViewHolder extends RecyclerView.ViewHolder {

            TextView mUserId;
            TextView mUserName;

            public UserViewHolder(View itemView) {
                super(itemView);

                mUserId = itemView.findViewById(R.id.user_id);
                mUserName = itemView.findViewById(R.id.user_name);
            }
        }
    }
}
