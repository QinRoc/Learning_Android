package com.example.homework_day_11_content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Roc on 2017/3/26.
 */

public class Homework2_ContentProvider extends ContentProvider {

    private SQLiteDatabase database;
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {

        Log.i("onCreate", "oncreate");

        Homework2_DB_OpenHelper db_openHelper = new Homework2_DB_OpenHelper(getContext(), "homework2.db", null, 1);
        database = db_openHelper.getWritableDatabase();

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI("com.roc.provider.auth", "users", 1);
        uriMatcher.addURI("com.roc.provider.auth", "goods", 2);

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int match = uriMatcher.match(uri);
        Cursor cursor = null;

        switch (match) {
            case 1:
                cursor = database.query("users", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 2:
                cursor = database.query("goods", projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }

        Log.i("query", cursor.toString());
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int match = uriMatcher.match(uri);
        switch (match) {
            case 1:
                database.insert("users", null, values);
                break;
            case 2:
                database.insert("goods", null, values);
                break;
        }

        Log.i("query", "插入成功");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int match = uriMatcher.match(uri);
        switch (match) {
            case 1:
                database.delete("users", selection, selectionArgs);
                break;
            case 2:
                database.delete("goods", selection, selectionArgs);
                break;
        }

        Log.i("query", "删除成功");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int match = uriMatcher.match(uri);
        switch (match) {
            case 1:
                database.update("users", values, selection, selectionArgs);
                break;
            case 2:
                database.update("goods", values, selection, selectionArgs);
                break;
        }

        Log.i("query", "更新成功");
        return 0;
    }
}
