package com.cskaoyan.week2.contentproviderdemoapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity provider";
    private SQLiteDatabase readableDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* MySqliteOpenHelper helper = new MySqliteOpenHelper(this,"data.db",null,1);
        readableDatabase = helper.getReadableDatabase();*/

    }

    public void query(View v) {

        Cursor cursor = readableDatabase.rawQuery("select * from user;", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String gender = cursor.getString(2);

            Log.i(TAG, "id=" + id + " name = " + name + " gender= " + gender);
        }

        //readableDatabase.execSQL("insert");

        cursor.close();
    }
}
