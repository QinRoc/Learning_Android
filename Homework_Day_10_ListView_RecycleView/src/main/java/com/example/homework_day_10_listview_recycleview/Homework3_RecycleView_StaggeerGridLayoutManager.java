package com.example.homework_day_10_listview_recycleview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class Homework3_RecycleView_StaggeerGridLayoutManager extends AppCompatActivity {

    private ArrayList<GoodsBean> goodsList;
    private SQLiteDatabase sqLiteDatabase;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__recycle_view);

        Log.i("onCreate", "oncreate run i");

        goodsList = new ArrayList<>();
        initalGoodsData();

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        GoodsListAdapter goodsListAdapter = new GoodsListAdapter();
        recyclerView.setAdapter(goodsListAdapter);
    }

    public void initalGoodsData() {
        GoodsDBOpenHelper goodsDBOpenHelper = new GoodsDBOpenHelper(this, "goods.db", null, 1);
        sqLiteDatabase = goodsDBOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from goods;", null);
        while (cursor.moveToNext()) {
            goodsList.add(new GoodsBean(cursor.getString(0), cursor.getInt(4), cursor.getString(1),
                    BigDecimal.valueOf(Double.valueOf(cursor.getString(2))), BigDecimal.valueOf(Double.valueOf(cursor.getString(3)))));
        }
        cursor.close();
    }


    private class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListAdapterHolder> {
        @Override
        public GoodsListAdapter.GoodsListAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            GoodsListAdapterHolder goodsListAdapterHolder = new GoodsListAdapterHolder(
                    LayoutInflater.from(Homework3_RecycleView_StaggeerGridLayoutManager.this).inflate(
                            R.layout.goods_list_items, parent, false));
            return goodsListAdapterHolder;
        }

        @Override
        public void onBindViewHolder(GoodsListAdapter.GoodsListAdapterHolder holder, int position) {

            /*ViewGroup.LayoutParams layoutParams = holder.origTV.getLayoutParams();
            switch (position%2){
                case 0:layoutParams.height=100;break;
                case 1:layoutParams.height=300;break;
            }
            holder.origTV.setLayoutParams(layoutParams);*///only change origTV

            ViewGroup.LayoutParams layoutParams = holder.goodsLL.getLayoutParams();
            switch (position % 2) {
                case 0:
                    layoutParams.height = 400;
                    break;
                case 1:
                    layoutParams.height = 600;
                    break;
            }
            holder.goodsLL.setLayoutParams(layoutParams);

            GoodsBean goods = goodsList.get(position);

            holder.titleTV.setText(goods.getGoodsTitle());
            holder.priceTV.setText(goods.getGoodsOrig().multiply(goods.getGoodsDiscount()).toString());
            holder.origTV.setText(goods.getGoodsOrig().toString());
            holder.origTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.photoIV.setImageResource(goods.getGoodsPhoto());

        }

        @Override
        public int getItemCount() {
            return goodsList.size();
        }

        class GoodsListAdapterHolder extends RecyclerView.ViewHolder {

            LinearLayout goodsLL;
            TextView titleTV;
            TextView priceTV;
            TextView origTV;
            ImageView photoIV;

            public GoodsListAdapterHolder(View itemView) {
                super(itemView);

                /*titleTV = (TextView) findViewById(R.id.goodsTitle);
                priceTV = (TextView) findViewById(R.id.goodsPrice);
                origTV = (TextView) findViewById(R.id.goodsOrig);
                photoIV = (ImageView) findViewById(R.id.goodsPhoto);*/

                titleTV = (TextView) itemView.findViewById(R.id.goodsTitle);
                priceTV = (TextView) itemView.findViewById(R.id.goodsPrice);
                origTV = (TextView) itemView.findViewById(R.id.goodsOrig);
                photoIV = (ImageView) itemView.findViewById(R.id.goodsPhoto);

                goodsLL = (LinearLayout) itemView.findViewById(R.id.goods);
            }
        }
    }


}
