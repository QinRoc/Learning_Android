package com.cskaoyan.week2.listviewdemoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhao on 2017/3/23.
 */

public class MyDataBaseOpenHelper extends SQLiteOpenHelper {
    public MyDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table stu (id integer,name varchar(20),age integer);");

        for (int i = 0; i < 40; i++) {
            db.execSQL("insert into stu values(?,?,?)", new Object[]{i, "name" + i, 18 + i});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 20; i < 40; i++) {
            db.execSQL("insert into stu values(?,?,?)", new Object[]{i, "name" + i, 18 + i});
        }
    }
}
