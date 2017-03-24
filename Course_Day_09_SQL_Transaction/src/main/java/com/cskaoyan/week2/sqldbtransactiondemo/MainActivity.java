package com.cskaoyan.week2.sqldbtransactiondemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="TransferMainActivity" ;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBOpenHelper helper = new MyDBOpenHelper(this,"bank.db",null,1);
        db = helper.getReadableDatabase();
    }

    //allen --> bill 100
    public void transfer(View v){

        int amount =100;
        String from ="allen";
        String to ="bill";

        try {
            db.beginTransaction();

            db.execSQL("update account set money = money -? where username = ?;", new Object[]{amount, from});
            int i = 1 / 0;
            db.execSQL("update account set money = money +? where username = ?;", new Object[]{amount, to});

            db.setTransactionSuccessful(); //设置事务提交的标识
            Log.i(TAG,"transfer done!");
        }
        catch (Exception e){
            Log.i(TAG,"transfer error!" );
        }finally {
            db.endTransaction();
            //提交 事务     如果有调用到setTransactionSuccessful()
            //回滚 事务     如果没有调用setTransactionSuccessful() 方法则回滚事务。
        }

    }

    public void query(View v){

        Cursor cursor = db.rawQuery("select * from account ;", null);

        while (cursor.moveToNext()){

            String username = cursor.getString(0);
            int money = cursor.getInt(1);

            Log.i(TAG,"username="+username+" money="+money);
        }

        cursor.close();
    }
}
