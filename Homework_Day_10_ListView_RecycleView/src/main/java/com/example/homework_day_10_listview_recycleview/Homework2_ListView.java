package com.example.homework_day_10_listview_recycleview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Homework2_ListView extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    //private StarBean star;
    private ArrayList<StarBean> stars;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2__list_view);

        listview = (ListView) findViewById(R.id.listview);
        stars = new ArrayList<>();

        initalStarsData();

        StarListAdaptor starListAdaptor = new StarListAdaptor();
        listview.setAdapter(starListAdaptor);

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

    class StarListAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return stars.size();
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

            StarBean star = stars.get(position);

            View view = null;

            if (convertView == null) {
                view = View.inflate(Homework2_ListView.this, R.layout.star_list_item, null);
                TextView idTV = (TextView) view.findViewById(R.id.starID);
                TextView nameTV = (TextView) view.findViewById(R.id.starName);
                TextView nationalityTV = (TextView) view.findViewById(R.id.starNationality);
                ImageView photoTV = (ImageView) view.findViewById(R.id.starPhoto);

                idTV.setText(star.getId());
                nameTV.setText(star.getName());
                nationalityTV.setText(star.getNationality());
                //photoTV.setImageResource();
                //photoTV.setImageDrawable(new Drawable() {}});
                photoTV.setImageResource(star.getPhoto());

                Holder holder = new Holder();
                holder.idTV = idTV;
                holder.nameTV = nameTV;
                holder.nationalityTV = nationalityTV;
                holder.photoTV = photoTV;

                view.setTag(holder);

            } else {
                view = convertView;
                Object tag = view.getTag();
                if (tag instanceof Holder) {
                    Holder holder = (Holder) tag;
                    holder.idTV.setText(star.getId());
                    holder.nameTV.setText(star.getName());
                    holder.nationalityTV.setText(star.getNationality());
                    holder.photoTV.setImageResource(star.getPhoto());
                }

            }

            return view;
        }

        class Holder {
            TextView idTV;
            TextView nameTV;
            TextView nationalityTV;
            ImageView photoTV;
        }
    }

}
