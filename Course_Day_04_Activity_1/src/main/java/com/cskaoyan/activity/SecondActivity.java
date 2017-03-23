package com.cskaoyan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.cskaoyan.bean.Student;
import com.cskaoyan.bean.User;
import com.cskaoyan.day2.day04activitydemo.R;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //启动当前Activity的意图
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");

        User user = (User) intent.getSerializableExtra("user");

        Student stu = (Student) intent.getParcelableExtra("stu");
        Log.i(TAG,name);
        Log.i(TAG,user.toString());
        Log.i(TAG,stu.toString());


        //可以在我自己关闭的时候，返回一些数据给到调用我的Activity。通过intent
        Intent intent_back = new Intent();
        intent_back.putExtra("result","thankyou");
        //把这intent_back作为结果返回回去
        setResult(300,intent_back);


    }

    public void colosAcitivity(View v){

        finish();
    }
}
