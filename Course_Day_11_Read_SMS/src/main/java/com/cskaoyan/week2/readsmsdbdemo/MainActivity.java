package com.cskaoyan.week2.readsmsdbdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "sms reader MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void read(View v) {

        //
        ContentResolver cr = getContentResolver();

        Cursor cursor = cr.query(
                Uri.parse("content://sms"),
                new String[]{"address", "body", "seen"},
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {

            String addr = cursor.getString(0);
            String body = cursor.getString(1);
            int seen = cursor.getInt(2);
            Log.i(TAG, "addr=" + addr + "body=" + body + "seen =" + seen);

        }


        cursor.close();

    }
}
