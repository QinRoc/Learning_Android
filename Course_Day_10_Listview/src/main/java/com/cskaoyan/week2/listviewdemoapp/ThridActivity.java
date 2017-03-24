package com.cskaoyan.week2.listviewdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

//GridView


public class ThridActivity extends AppCompatActivity {


    ArrayList<String> data;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);

        gridView = (GridView) findViewById(R.id.gridview);
        //1 .准备数据
        initData();
        //2 准备适配器
        MyAdapter adapter = new MyAdapter();
        //3 适配器给到gridview
        gridView.setAdapter(adapter);

    }


    private void initData() {

        data = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            data.add("item" + i);
        }

    }

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(ThridActivity.this);
            tv.setText(data.get(position));
            return tv;
        }
    }
}
