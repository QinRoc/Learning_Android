package com.cskaoyan.week3.servicedemoapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ServiceConnection connection;
    private MyService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, MyListenCallService.class));

        /*new Thread(new Runnable() {
            @Override
            public void run() {

                int i=0;
                while(i<100){
                    i++;
                    Log.i(TAG,"i="+i);

                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
    }

    //负责启动服务
    public void startservice(View v) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    //停止服务
    public void stop(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    //通过绑定方式启动服务
    public void bind(View v) {
        Intent intent = new Intent(this, MyService.class);
        connection = new MyServiceConnection();
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    //通过解绑方式停止服务
    public void unbind(View v) {
        if (connection != null)
            unbindService(connection);
    }

    //需要调用服务里的方法：
    public void call(View v) {
        if (myBinder != null) {
            String serviceNameXXX = myBinder.getServiceNameXXX();
            int countXXX = myBinder.getCountXXX();
            Toast.makeText(this, serviceNameXXX + countXXX, Toast.LENGTH_SHORT).show();
        }
    }

    public void directCall(View v) {
        //自己new出来是service跟启动系统的servcie一样吗？不一样，就是最普通的方法调用。
        //系统的service是一个系统组件。自己new出来的service就是一个最普通的类。
        MyService myService = new MyService();
        String serviceName = myService.getServiceName();
        int count = myService.getCount();
        Toast.makeText(this, serviceName + count, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
    }

    //能否成功建立连接需要service去确定
    class MyServiceConnection implements ServiceConnection {

        //当连接上的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {

            myBinder = (MyService.MyBinder) binder;

            Log.i(TAG, "onServiceConnected" + binder.hashCode());
        }

        //当连接断开的时候调用
        //该方法只在Service被破坏了或者被杀死的时候调用.
        //例如, 系统资源不足, 要关闭一些Services, 刚好连接绑定的 Service 是被关闭者之一,  这个时候onServiceDisconnected() 就会被调用。
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
        }
    }
}
