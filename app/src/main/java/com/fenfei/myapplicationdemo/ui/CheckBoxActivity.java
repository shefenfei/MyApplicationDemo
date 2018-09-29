package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.User;
import com.fenfei.myapplicationdemo.interfaces.RecyclerViewOnItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//recyclerView.setNestedScrollingEnabled(false); 就ok了
public class CheckBoxActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayout mLinearLayout;

    private List<User> mUsers = new ArrayList<>();
    // 存储勾选框状态的map集合
    private Map<Integer, Boolean> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        initViews();
        initData();
        testDate();
    }

    private void testDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.WEEK_OF_MONTH , -1);
        Date dateFrom = calendar.getTime();
        Log.e("时间是：", "testDate: " + format.format(dateFrom) + "到" + format.format(dateNow));
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.user_list);
        mLinearLayout = (LinearLayout) findViewById(R.id.selected_layout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initData() {
        User user;
        for (int i = 0; i < 25; i++) {
            user = new User();
            user.setUid("uid" + i);
            user.setUsername("username-" + i);
            mUsers.add(user);
        }

        final MyRecyclerAdapter adapter = new MyRecyclerAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setRecyclerViewOnItemClickListener(new RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                adapter.setSelectItem(position);
                if (map.get(position)) {
                    TextView tv = new TextView(view.getContext());
                    tv.setText(mUsers.get(position).getUsername());
                    tv.setTag(position);
                    mLinearLayout.addView(tv);
                }else {
                    mLinearLayout.removeView(mLinearLayout.findViewWithTag(position));
                }
                Log.e("TAG", "onItemClickListener: " + mUsers.get(position).getUsername() );
            }

            @Override
            public boolean onItemLongClickListener(View view, int position) {
                return false;
            }
        });
    }


    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.UserViewHolder> implements View.OnClickListener{


        private RecyclerViewOnItemClickListener mOnItemClickListener;

        public MyRecyclerAdapter() {
            initMap();
        }

        //初始化map集合,默认为不选中
        private void initMap() {
            for (int i = 0; i < mUsers.size(); i++) {
                map.put(i, false);
            }
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_layout , parent , false);
            UserViewHolder viewHolder = new UserViewHolder(view);
            view.setOnClickListener(this);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, final int position) {
            User user = mUsers.get(position);
            holder.username.setText(user.getUsername());

            holder.root.setTag(position);
            holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    map.put(position , b);
                }
            });

            if (map.get(position) == null) {
                map.put(position , false);
            }
            holder.checkbox.setChecked(map.get(position));
        }

        //设置点击事件
        public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClickListener(view, (Integer) view.getTag());
            }
        }

        //点击item选中CheckBox
        public void setSelectItem(int position) {
            //对当前状态取反
            if (map.get(position)) {
                map.put(position, false);
            } else {
                map.put(position, true);
            }
            notifyItemChanged(position);
        }

        class UserViewHolder extends RecyclerView.ViewHolder {

            CheckBox checkbox;
            TextView username;
            View root;

            public UserViewHolder(View itemView) {
                super(itemView);
                this.root = itemView;
                checkbox = (CheckBox) itemView.findViewById(R.id.checkbox_user);
                username = (TextView) itemView.findViewById(R.id.user_name);
            }
        }
    }

}
