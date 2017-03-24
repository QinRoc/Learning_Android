package com.cskaoyan.week2.contentproviderdemoapp;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhao on 2017/3/24.
 */

public class MyContentProvider extends ContentProvider {

    private static final String TAG = "MyContentProvider";
    private SQLiteDatabase readableDatabase;
    private UriMatcher sMatcher;

    //当MyContentProvider被创建的时候会调用到onCreate
    //MyContentProvider 什么时候创建？
    //contentprovider被创建的时候会执行onCreate
    //这里面基本上就是要初始化一个sqliteDatabase对象
    @Override
    public boolean onCreate() {
        MySqliteOpenHelper helper = new MySqliteOpenHelper(getContext(), "data.db", null, 2);
        readableDatabase = helper.getReadableDatabase();

        //匹配器
        sMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //"content://com.cskaoyan.provider.auth/goods"  1
        //"content://com.cskaoyan.provider.auth/user"   2

        //"content://com.cskaoyan.provider.auth/goods/6"

        sMatcher.addURI("com.cskaoyan.provider.auth", "goods", 1);
        sMatcher.addURI("com.cskaoyan.provider.auth", "user", 2);
        sMatcher.addURI("com.cskaoyan.provider.auth", "goods/#", 3);
        sMatcher.addURI("com.cskaoyan.provider.auth", "goods/*", 4);


        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        int match = sMatcher.match(uri);


       /* String s1 = uri.getPathSegments().get(0);
        String s2 = uri.getPathSegments().get(1);

        Log.i(TAG,"s1="+s1);
        Log.i(TAG,"s2="+s2);*/


        Cursor cursor = null;
        if (match == 1) { //访问goods表
            cursor = readableDatabase.query("goods", projection, selection, selectionArgs, null, null, sortOrder);
        } else if (match == 2) {
            cursor = readableDatabase.query("user", projection, selection, selectionArgs, null, null, sortOrder);
        } else if (match == 3) {
            //cursor = readableDatabase.query("goods",projection,selection,selectionArgs,null,null,sortOrder);
            long l = ContentUris.parseId(uri);

            Log.i(TAG, "id=" + l);
            cursor = readableDatabase.rawQuery("select * from goods limit ?;", new String[]{l + ""});

        } else if (match == 4) {
            cursor = readableDatabase.rawQuery("select * from goods limit ?;", new String[]{"3"});

        }


        if (cursor == null)
            Log.i("provider", "cursor is null");
        else
            Log.i("provider", cursor.toString());


        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        int match = sMatcher.match(uri);
        if (match == 1) { //访问goods表
            readableDatabase.insert("goods", null, values);

            //当这个表发生变化之后，我们可以通知系统。
            //那么 ，如果有人跟系统注册了这个数据库的表发生变化需要得到通知，系统就会通知那个APP
            ContentResolver contentResolver = getContext().getContentResolver();
            contentResolver.notifyChange(uri, null);

        } else if (match == 2) {
            readableDatabase.insert("user", null, values);
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        readableDatabase.delete("user", selection, selectionArgs);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {


        readableDatabase.update("user", values, selection, selectionArgs);
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
