package com.example.homework_day_10_listview_recycleview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Roc on 2017/3/25.
 */

public class GoodsDBOpenHelper extends SQLiteOpenHelper {
    public GoodsDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table goods (id varchar(20) primary key, title varchar(200) not null, orig varchar(10) not null, discount char(6) not null, photo int not null);");

        db.execSQL("insert into goods values (?,?,?,?,?);", new Object[]{"100", "木梳", "99", "0.8", R.drawable.ms});
        db.execSQL("insert into goods values (?,?,?,?,?);", new Object[]{"101", "火绒", "20", "1", R.drawable.hr});
        db.execSQL("insert into goods values (?,?,?,?,?);", new Object[]{"102", "水润", "30", "0.99", R.drawable.sr});
        db.execSQL("insert into goods values (?,?,?,?,?);", new Object[]{"103", "金笔", "199", "0.95", R.drawable.jb});
        db.execSQL("insert into goods values (?,?,?,?,?);", new Object[]{"104", "土产", "42", "0.86", R.drawable.tc});
        db.execSQL("insert into goods values (?,?,?,?,?);", new Object[]{"105", "月白", "68", "0.6", R.drawable.yb});
        db.execSQL("insert into goods values (?,?,?,?,?);", new Object[]{"106", "日抛", "102", "0.5", R.drawable.rp});

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
