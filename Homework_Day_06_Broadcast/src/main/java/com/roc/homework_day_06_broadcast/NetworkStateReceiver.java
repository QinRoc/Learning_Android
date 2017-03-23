package com.roc.homework_day_06_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.roc.homework_day_06_broadcast.NetworkUtil.getConnectivityStatusString;

/**
 * Created by Peng on 2017/3/18.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String newworkState = getConnectivityStatusString(context);
        Log.i("Network State",newworkState);
        Toast.makeText(context, newworkState, Toast.LENGTH_SHORT).show();
    }
}
