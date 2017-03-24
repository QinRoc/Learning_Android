package com.cskaoyan.week2.contentaccesserdemoapp;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

/**
 * Created by zhao on 2017/3/24.
 */

public class MyContentObserver extends ContentObserver {

    public MyContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        Log.i("tag", "database changed!");
    }
}
