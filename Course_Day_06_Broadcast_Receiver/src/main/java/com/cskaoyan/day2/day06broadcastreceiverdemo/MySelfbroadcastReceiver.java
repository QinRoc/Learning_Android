package com.cskaoyan.day2.day06broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MySelfbroadcastReceiver extends BroadcastReceiver {

    private  static  final  String TAG ="MySelfReceiver1";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        String key = intent.getStringExtra("key");
        Log.i(TAG,"received " +intent.getAction() +key);
    }
}
