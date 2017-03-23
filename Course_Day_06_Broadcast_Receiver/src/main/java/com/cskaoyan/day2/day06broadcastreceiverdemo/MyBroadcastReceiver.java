package com.cskaoyan.day2.day06broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhao on 2017/3/18.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "MyBroadcastReceiver";

    //当接收广播的（数据） 的时候调用，数据在intent里面。
    //当锁屏的时候，系统会发广播。我们已经注册。
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action.equals(Intent.ACTION_PACKAGE_ADDED))
            Log.i(TAG,"安装了一个新应用");
        else  if (action.equals(Intent.ACTION_PACKAGE_REMOVED))
            Log.i(TAG,"卸载了一个新应用");
        else  if (action.equals(Intent.ACTION_PACKAGE_REPLACED))
            Log.i(TAG,"更新了一个新应用");
    }
}
