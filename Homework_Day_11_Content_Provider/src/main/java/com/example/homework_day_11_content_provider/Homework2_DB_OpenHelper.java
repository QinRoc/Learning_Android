package com.example.homework_day_11_content_provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Roc on 2017/3/26.
 */

public class Homework2_DB_OpenHelper extends SQLiteOpenHelper {


    public Homework2_DB_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users (id integer,name varchar(20),gender char);");
        for (int i = 0; i < 10; i++) {
            db.execSQL("insert into users values (?,?,?);", new Object[]{i, "name" + i, '男'});
        }

        db.execSQL("create table goods (id integer,name varchar(20),price integer);");
        for (int i = 0; i < 10; i++) {
            db.execSQL("insert into goods values (?,?,?);", new Object[]{i, "product" + i, i * 10});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
