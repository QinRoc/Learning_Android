package com.roc.homework_day_06_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Peng on 2017/3/18.
 */

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
        super();
    }

    private  static  final  String TAG ="BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Boot now");
        Toast.makeText(context, "Boot now!", Toast.LENGTH_LONG).show();
        //jumpToGallery();

        Intent intentBoot = new Intent();
        intentBoot.setAction("android.intent.action.MAIN" );
        intentBoot.addCategory("android.intent.category.LAUNCHER");
        context.startActivity(intentBoot);
    }

    /*public void jumpToGallery(){

        //以下代码是跳到图库Activity的
        *//*<intent-filter>
                <action android:name="android.intent.action.PICK" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="image*//**//*" />
                <data android:mimeType="video*//**//*" />
            </intent-filter>*//*
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK" );
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("image/jpeg");
        //ActivityCompat.startActivityForResult(MainActivity,intent,100);

    }*/
}
