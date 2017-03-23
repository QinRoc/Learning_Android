package com.cskaoyan.day2.day06broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MySelfbroadcastReceiver2 extends BroadcastReceiver {
    private  static  final  String TAG ="MySelfReceiver2";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.i(TAG,"received " +intent.getAction()  );

        //拦截广播
        abortBroadcast();
    }
}
