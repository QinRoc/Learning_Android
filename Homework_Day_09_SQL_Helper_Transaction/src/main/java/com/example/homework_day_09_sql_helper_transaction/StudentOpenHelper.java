package com.example.homework_day_09_sql_helper_transaction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Roc on 2017/3/24.
 */

public class StudentOpenHelper extends SQLiteOpenHelper {


    public StudentOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //super(context, "Students.db", null, 1);
    }

    /*public StudentOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("create table students (id Integer primary key autoincrement, name varchar(20), gender char(2), class varchar(15));");
        db.execSQL("create table students (id char(10) primary key, name varchar(20), gender char(2), studentClass varchar(15));");
        db.execSQL("insert into students values(1,'lucy','女','1-1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {//this version is not VersionCode, but database version
            //db.execSQL("alter table students add (major varchar(50));");
            db.execSQL("alter table students add column major varchar(50);");
            Log.i("onUpgrade", "add major");
        }
    }
}
