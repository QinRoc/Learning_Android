package com.example.homework_day_10_listview_recycleview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Roc on 2017/3/25.
 */

public class StarsDBOpenHelper extends SQLiteOpenHelper {

    public StarsDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table stars (id varchar(10) primary key, name varchar(20) not null, nationality varchar(20), photo varchar(50) not null;");
        db.execSQL("create table stars (id varchar(10) primary key, name varchar(20) not null, nationality varchar(20), photo int not null);");

        /*db.execSQL("insert into stars (?,?,?,?);", new Object[]{"100","秦昊","中国","@drawable/qh"});
        db.execSQL("insert into stars (?,?,?,?);", new Object[]{"101","彭于晏","中国","@drawable/pyy"});
        db.execSQL("insert into stars (?,?,?,?);", new Object[]{"102","白百合","中国","@drawable/bbh"});
        db.execSQL("insert into stars (?,?,?,?);", new Object[]{"103","刘青云","中国香港","@drawable/lqy"});
        db.execSQL("insert into stars (?,?,?,?);", new Object[]{"104","陈妍希","中国台湾","@drawable/acyx"});
        */

        db.execSQL("insert into stars values (?,?,?,?);", new Object[]{"100", "秦昊", "中国", R.drawable.qh});
        db.execSQL("insert into stars values (?,?,?,?);", new Object[]{"101", "彭于晏", "中国", R.drawable.pyy});
        db.execSQL("insert into stars values (?,?,?,?);", new Object[]{"102", "白百合", "中国", R.drawable.bbh});
        db.execSQL("insert into stars values (?,?,?,?);", new Object[]{"103", "刘青云", "中国香港", R.drawable.lqy});
        db.execSQL("insert into stars values (?,?,?,?);", new Object[]{"104", "陈妍希", "中国台湾", R.drawable.cyx});

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
