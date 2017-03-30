package com.cskaoyan.week3.servicedemoapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhao on 2017/3/30.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";

    boolean flag = true;
    int count = 0;

    //目前用不上。
    // 这里返回的是null 就没法连接。
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //服务:数数。
                while (flag) {
                    count++;
                    Log.i(TAG, "count=" + count);
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

       /* IBinder iBinder = new MyBinder();
        Log.i(TAG,"onBind" +iBinder.hashCode());
        return iBinder;*/

        return new MyRemoteBinder();
    }

    public int getCount() {
        return count;
    }

    public String getServiceName() {
        return "myservice!";
    }

    //当service被创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    //service启动的时候调用
    //这里阻塞了主线程。
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    //service停止的时候调用
    @Override
    public void onDestroy() {
        flag = false;
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    class MyBinder extends Binder {

        public int getCountXXX() {
            return MyService.this.getCount();
        }

        public String getServiceNameXXX() {
            return MyService.this.getServiceName();
        }
    }

    class MyRemoteBinder extends IMyAidlInterface.Stub {

        @Override
        public int getCount() throws RemoteException {
            return MyService.this.getCount();
        }

        @Override
        public String getServiceName() throws RemoteException {
            return MyService.this.getServiceName();
        }
    }
}
