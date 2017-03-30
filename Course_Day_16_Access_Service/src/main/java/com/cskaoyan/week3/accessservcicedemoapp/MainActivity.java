package com.cskaoyan.week3.accessservcicedemoapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cskaoyan.week3.servicedemoapp.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyConnection conn;
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void bind(View v) {

        /*Intent intent = new Intent();
        intent.setAction("com.cskoayan.myservice");*/

        // Service Intent must be explicit:
        // Intent { act=com.cskaoyan.zhao.day16servicedemo.myservice }
        Intent intent = new Intent();
        intent.setAction("com.cskoayan.myservice");
        intent.setPackage("com.cskaoyan.week3.servicedemoapp");

        conn = new MyConnection();
        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    public void unbind(View v) {
        unbindService(conn);
    }


    //远程调用服务中的方法
    public void remoteCall(View v) {

        //调用远程服务的getCount()

        if (iMyAidlInterface != null) {

            try {
                String serviceName = iMyAidlInterface.getServiceName();
                int count = iMyAidlInterface.getCount();
                Toast.makeText(this, serviceName + count, Toast.LENGTH_SHORT).show();

            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }

    }

    class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //myBinder = (MyService.MyBinder) binder;
            //如何把远程服务反回来的一个binder对象还原成一个可以用的对象。
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.i(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
