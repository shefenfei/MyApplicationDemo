package com.fenfei.myapplicationdemo.studemo.day05;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.studemo.day05.absfactory.AbstractMsgChannelFactory;
import com.fenfei.myapplicationdemo.studemo.day05.absfactory.TvFactory;
import com.fenfei.myapplicationdemo.studemo.day05.proxy.IUserManager;
import com.fenfei.myapplicationdemo.studemo.day05.proxy.UserManagerProxy;
import com.fenfei.myapplicationdemo.studemo.day05.proxy.impl.IUserManagerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 使用 ViewModel 在 fragment 之间共享数据 ，替换 使用Listener 方式
 */
public class Day05Activity extends AppCompatActivity {

    String TAG = "Day05Activity";

    private DemoFactory mDemoFactory;
    private AbstractMsgChannelFactory mAbstractFactory;

    private IUserManager mIUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day05);

        testArgs();

        testDiscount();
//        createSimpleFactory();

//        createAbstractFactory();
    }

    @TargetApi(24)
    private void testDiscount() {
        List<String> strings = Arrays.asList("a" , "b" , "c" , "a111" , "12" , "a");
        strings = strings.stream().distinct().collect(Collectors.toList());
        Log.e(TAG, "testDiscount: " + strings );
        ArrayList arrayList;
    }

    private void createAbstractFactory() {
        mAbstractFactory = new TvFactory();

        mIUserManager = (IUserManager) new UserManagerProxy().newProxyInstance(new IUserManagerImpl());
    }

    private void createSimpleFactory() {
        mDemoFactory = new TvDemoFactoryImpl();
        TvBean tvBean = mDemoFactory.createChannel();
    }

    private void testArgs() {
        String tel = "13040691917";
        String regx = "(?<=\\d{3})\\d(?=\\d{4})";
        String s = tel.replaceAll(regx , "*");
        Log.e(TAG, "testArgs: " + s );


        Map<String, Object> map = new HashMap<>();

        String[] codes = {"123" ,"abc" , "111" , "888" , "999" , "11111"};
        Set<String> users = new HashSet<>();
        users.add("shefenfei");
        users.add("zhangwentao");
        users.add("zhangsan");
        users.add("lisi");
        users.add("wangwu");
        users.add("zhang6");


        String content = "这是你的核销码 : [核销码] 到店使用";

        Iterator<String> iterator = users.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            String user = iterator.next();
            Log.e(TAG, "onCreate: " + user );
            String code = codes[index];
            if (content.contains("[核销码]")) {
                String newStr = content.replace("[核销码]" , code);
                map.put(user , newStr);
            }else {
                map.put(user , content);
            }
            index ++ ;
        }

        Log.e(TAG, "onCreate: " + map.toString());
    }



    private void testViewPager() {
        ViewPager viewPager = new ViewPager(this);
        viewPager.addView(null);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);

            }
        });
    }


    private class Myadapter extends ArrayAdapter<String> {

        int resId = -1;
        Button btn1;
        Button btn2;
        Button btn3;

        public Myadapter(@NonNull Context context, int resource) {
            super(context, resource);
            resId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(resId , parent , false);


            return super.getView(position, convertView, parent);
        }

        //如果id不变，listview不会重新绘view
        @Override
        public boolean hasStableIds() {
            return true; //返回true
        }
    }
}
