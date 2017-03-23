package com.cskaoyan.day2.day06broadcastreceiverdemo;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyScreenOffReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS)!= PackageManager.PERMISSION_GRANTED){
             ActivityCompat.requestPermissions(this,
                     new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS,
                                   Manifest.permission.RECEIVE_SMS},
                     0);
         }

         //广播接受者的第二种接收方式 ： 动态注册
         //第一个参数是一个BroadcastReceiver
        // 第二个参数 是一个意图过滤器
        // 动态注册的广播，应用退出之后能收到吗
        receiver = new MyScreenOffReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver,intentFilter);

        //动态注册的广播，在退出的时候应该手动的去取消注册。
    }

    //动态注册的广播，在退出的时候应该手动的去取消注册。
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode==0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "已经获取到监听外拨电话的权限!", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "已经获取到接收短信的权限!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
