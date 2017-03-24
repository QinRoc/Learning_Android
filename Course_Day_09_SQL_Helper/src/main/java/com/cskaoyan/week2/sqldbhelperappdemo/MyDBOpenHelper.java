package com.cskaoyan.week2.sqldbhelperappdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by zhao on 2017/3/22.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "MyDBOpenHelper";

    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, "bank.db", null, 1);
    }

    //当数据库创建的时候调用；
    //只会调用一次
    @Override
    public void onCreate(SQLiteDatabase db) {

        //初始化数据库用的
        //建表
        db.execSQL("create table user (username varchar(20),gender char,telephone varchar(15));");
        //往表里插入初始化数据
        db.execSQL("insert into user values('allen','女','13632838888')");

        Log.i(TAG,"onCreate");
    }

    //更新的时候
    //数据库升级的时候，在此新增一张表
    //第一次升级的时候，才会执行到
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"onUpgrade");

        if (newVersion==2){
            db.execSQL("create table products (id integer , price integer, name varchar(12));");
            Log.i(TAG,"onUpgrade when newversion ==3");
        }
        if (newVersion==3){
            db.execSQL("create table products (id integer , price integer, name varchar(12));");
            Log.i(TAG,"onUpgrade when newversion ==3");
        }
    }
}
