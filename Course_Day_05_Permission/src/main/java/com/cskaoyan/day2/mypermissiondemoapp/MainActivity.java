package com.cskaoyan.day2.mypermissiondemoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View v){

        Intent intent = new Intent();
        //ACTION_DIAL ：跳转到拨号器页面，由拨号器进行具体的打电话 不用申请权限
        //ACTION_CALL ：直接打出去电话
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://110"));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED){
            //有权限，直接打
            startActivity(intent);
        }else{
            //说明当前的app没有打电话的权限，此时需要去动态申请授权
            //采用异步机制
             ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    100);
        }
    }

    //requestPermissions 返回结果的时候会回调到该函数
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            Log.i(TAG,permissions[0]);

            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //此时说明用户同意了授权
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://110"));
                startActivity(intent);
            }else{
                Toast.makeText(this,"用户不同意！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
