package com.cskaoyan.week2.contentaccesserdemoapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity Accessor";
    private ContentResolver contentResolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //怎么要 调用什么api？
        contentResolver = getContentResolver();

        //注册一个内容观察者
        //要观察谁？
        //当你观察的对象发生变化之后，怎么去通知你
        contentResolver.registerContentObserver(
                Uri.parse("content://com.cskaoyan.provider.auth/goods"),
                false,
                new MyContentObserver(null)
        );

        //注意，这里能不能接收到数据库变化的通知，取决于数据库发生变化之后，有没有通知系统。

    }

    public void query(View v) {

        //向系统要某个应用的数据


        //要哪个？
        //@NonNull Uri uri,
        /*@Nullable String[] projection,
        @Nullable String selection,
        @Nullable String[] selectionArgs,
        @Nullable String sortOrder*/
       /* Cursor cursor = contentResolver.query(Uri.parse("content://com.cskaoyan.provider.auth/goods/6"),
                new String[]{"id","name"},
                null,
                null,
                null);*/

        Cursor cursor = contentResolver.query(Uri.parse("content://com.cskaoyan.provider.auth/goods"),
                new String[]{"id", "name"},
                null,
                null,
                null);


        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            Log.i(TAG, "id=" + id + " name = " + name);
            //String gender = cursor.getString(2);

            // Log.i(TAG,"id="+id +" name = " +name +" gender= "+gender);
        }

        cursor.close();


    }

    public void update(View v) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", "6666");

        contentResolver.update(Uri.parse("content://com.cskaoyan.provider.auth"),
                contentValues,
                "id = ?",
                new String[]{"6"}
        );

    }

    public void delete(View v) {

        contentResolver.delete(Uri.parse("content://com.cskaoyan.provider.auth"),
                "id = ?",
                new String[]{"10"}
        );

    }

    public void insert(View v) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 10);
        contentValues.put("name", "product10");
        contentValues.put("price", "100");

        contentResolver.insert(Uri.parse("content://com.cskaoyan.provider.auth/goods"),
                contentValues
        );

    }
}
