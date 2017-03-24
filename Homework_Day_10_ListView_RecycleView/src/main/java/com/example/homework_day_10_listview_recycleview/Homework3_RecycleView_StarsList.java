package com.example.homework_day_10_listview_recycleview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Homework3_RecycleView_StarsList extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<StarBean> stars;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__recycle_view);

        stars = new ArrayList<>();
        initalStarsData();

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StarListAdapter starListAdapter = new StarListAdapter();
        recyclerView.setAdapter(starListAdapter);

    }

    public void initalStarsData() {
        StarsDBOpenHelper starsDBOpenHelper = new StarsDBOpenHelper(this, "stars.db", null, 1);
        sqLiteDatabase = starsDBOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from stars;", null);
        while (cursor.moveToNext()) {
            /*star.setId(cursor.getString(0));
            star.setName(cursor.getString(1));
            star.setNationality(cursor.getString(2));
            star.setPhoto(cursor.getString(3));*/
            stars.add(new StarBean(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3)));
            //star=null;
        }
        cursor.close();
    }

    class StarListAdapter extends RecyclerView.Adapter<StarListAdapter.StarListAdapterHolder> {

        @Override
        public StarListAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            StarListAdapterHolder starListAdapterHolder = new StarListAdapterHolder(
                    LayoutInflater.from(Homework3_RecycleView_StarsList.this).inflate(
                            R.layout.star_list_item, parent, false)
            );
            return starListAdapterHolder;
        }

        @Override
        public void onBindViewHolder(StarListAdapterHolder holder, int position) {
            StarBean star = stars.get(position);

            holder.idTV.setText(star.getId());
            holder.nameTV.setText(star.getName());
            holder.nationalityTV.setText(star.getNationality());
            holder.photoIV.setImageResource(star.getPhoto());
        }

       /* @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }*/

        @Override
        public int getItemCount() {
            return stars.size();
        }

        class StarListAdapterHolder extends RecyclerView.ViewHolder {
            TextView idTV;
            TextView nameTV;
            TextView nationalityTV;
            ImageView photoIV;

            public StarListAdapterHolder(View view) {
                super(view);
                idTV = (TextView) view.findViewById(R.id.starID);
                nameTV = (TextView) view.findViewById(R.id.starName);
                nationalityTV = (TextView) view.findViewById(R.id.starNationality);
                photoIV = (ImageView) view.findViewById(R.id.starPhoto);
            }
        }
    }

}
