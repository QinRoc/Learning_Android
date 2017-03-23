package com.roc.homework_day_06_broadcast;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private CallReceiver callReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Send","oncreate run i");

        callReceiver = new CallReceiver();
        //Intent intent = new Intent();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(500);
        intentFilter.addAction("com.roc.comrade");
        registerReceiver(callReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(callReceiver);
    }
}
