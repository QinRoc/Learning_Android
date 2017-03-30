package com.example.homework_day_16_service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import static android.content.Intent.ACTION_SCREEN_OFF;

public class ListenService extends Service {

    final static String TAG = "ListenService";
    private ScreenOffBR screenOffBR;

    public ListenService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    /*@Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //regeister the broadcast receiver
        Log.i(TAG, "onStartCommand");
        registerBR();

        return super.onStartCommand(intent, flags, startId);
    }

    public void registerBR() {
        IntentFilter intentFilter = new IntentFilter(ACTION_SCREEN_OFF);
        screenOffBR = new ScreenOffBR();
        registerReceiver(screenOffBR, intentFilter);
        Log.i(TAG, "registerBR:注册完成");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Logout the broadcast receiver
        Log.i(TAG, "onDestroy");
        //unregisterBR();
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i(TAG, "onBind");
        registerBR();

        return new ListenBinder();
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {

        //Logout the broadcast receiver
        Log.i(TAG, "onUnbind");
        unregisterBR();
        return super.onUnbind(intent);
    }

    public void unregisterBR() {
        //unregisterReceiver(new ScreenOffBR());
        unregisterReceiver(screenOffBR);
        Log.i(TAG, "unregisterBR:解除注册");
    }

    class ScreenOffBR extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "screen off", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "ScreenOffBR:onReceive:锁屏");
        }
    }

    class ListenBinder extends Binder {

        public ListenBinder() {
            super();
        }

        public void register() {
            ListenService.this.registerBR();
        }

        public void unregister() {
            ListenService.this.unregisterBR();
        }
    }
}
