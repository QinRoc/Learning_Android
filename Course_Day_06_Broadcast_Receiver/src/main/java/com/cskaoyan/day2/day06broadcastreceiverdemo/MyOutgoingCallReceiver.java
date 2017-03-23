package com.cskaoyan.day2.day06broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyOutgoingCallReceiver extends BroadcastReceiver {

    private  static  final  String TAG ="MyOutgoingCallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        // Uri data = intent.getData();
        String resultData = getResultData();
        Log.i(TAG,"outgoing call number is " +resultData);
        setResultData("17951" + resultData);
    }
}
