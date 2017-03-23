package com.cskaoyan.week2.sqldbhelperappdemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这句话执行完毕后，数据库有创建吗？ 没有数据库
        //需要注意，只能往高版本走，不能往回走。
        //Caused by: android.database.sqlite.SQLiteException: Can't downgrade database from version 3 to 2
        //MyDBOpenHelper helper = new MyDBOpenHelper(this,"mydata.db",null,2);
        MyDBOpenHelper helper = new MyDBOpenHelper(this,null,null,0);
        //readableDatabase
        //拿到一个可读的数据库？
        //创建或者打开 一个数据库
        //This will be the same object returned by getWritableDatabase
        //some problem, such as a full disk,
        //requires the database to be opened read-only.
//        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
          SQLiteDatabase writableDatabase = helper.getWritableDatabase();

    }
}
