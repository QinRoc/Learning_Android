package com.cskaoyan.week2.contentproviderdemoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhao on 2017/3/24.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {
    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table user (id integer ,name varchar(20),gender char);");

        for (int i = 0; i < 10; i++) {
            db.execSQL("insert into user values (?,?,?);", new Object[]{i, "name" + i, '男'});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("create table goods (id integer ,name varchar(20),price integer);");
        for (int i = 0; i < 10; i++) {
            db.execSQL("insert into goods values (?,?,?);", new Object[]{i, "product" + i, i * 10});
        }
    }
}
