package com.roc.homework_day_06_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Peng on 2017/3/18.
 */

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //IntentFilter intentFilter = new IntentFilter("com.roc.comrade");
        //registerReceiver.
        String call = intent.getStringExtra("Call");
        Log.i("Call",call);
        Toast.makeText(context, call, Toast.LENGTH_LONG).show();

        intent.setAction("android.intent.action.PICK" );
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("image/jpeg");
        context.startActivity(intent);
    }
}
