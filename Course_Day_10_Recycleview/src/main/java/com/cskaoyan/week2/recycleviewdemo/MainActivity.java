package com.cskaoyan.week2.recycleviewdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycleview;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleview = (RecyclerView) findViewById(R.id.recycleview);

        //初始化数据
        initData();

        //设置一个布局管理器
        //LinearLayoutManager 实现了类似于listview 的效果
        //recycleview.setLayoutManager(new LinearLayoutManager(this));

        //GridLayoutManager 实现了类似于GridView的效果
        //recycleview.setLayoutManager(new GridLayoutManager(this,4));

        //StaggeredGridLayoutManager
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recycleview.setLayoutManager(staggeredGridLayoutManager);

        //RecyclerView.ItemDecoration
        recycleview.addItemDecoration(new MyItemDecoration());

        //设置Adapter
        MyAdapter adapter = new MyAdapter();
        recycleview.setAdapter(adapter);

        /*recycleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        // recycleview.setOnLongClickListener();

    }

    private void initData() {

        mDatas = new ArrayList<>();

        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //View view = View.inflate(MainActivity.this, R.layout.item, parent);
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
            MyViewHolder myViewholder = new MyViewHolder(view);
            return myViewholder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            //去更改一下控件的高度

            ViewGroup.LayoutParams layoutParams = holder.tv.getLayoutParams();
            if (position % 3 == 0) {
                layoutParams.height = 200;
            } else if (position % 3 == 1) {
                layoutParams.height = 100;
            } else {
                layoutParams.height = 240;
            }
            holder.tv.setLayoutParams(layoutParams);

            String s = mDatas.get(position);
            holder.tv.setText(s);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }


    class MyItemDecoration extends RecyclerView.ItemDecoration {


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //super.getItemOffsets(outRect, view, parent, state);

            outRect.top = 8;
            outRect.left = 8;
            outRect.right = 8;
            outRect.bottom = 8;

        }
    }

}
