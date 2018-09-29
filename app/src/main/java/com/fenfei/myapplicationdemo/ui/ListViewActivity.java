package com.fenfei.myapplicationdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.designpattern.factroypattern.ChicagoPizzaStore;
import com.fenfei.myapplicationdemo.designpattern.factroypattern.PizzaStrore;

import java.util.LinkedList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView mListView_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView_ = (ListView) findViewById(R.id.listview);

        final LinkedList<String> dataset = new LinkedList<>();

        String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14", "15", "16",
                "17", "18", "19", "20"};
        for (String s : arr) {
            dataset.add(s);
        }
        final MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, dataset);
        mListView_.setAdapter(adapter);

        mListView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = dataset.get(position);
                dataset.remove(position);
                adapter.notifyDataSetChanged();
                mListView_.setSelection(position);
                Log.e("listview", "onItemClick: " + str);

                PizzaStrore strore = new ChicagoPizzaStore();
                strore.orderPizza("Chicago");
            }
        });
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

    }

}
