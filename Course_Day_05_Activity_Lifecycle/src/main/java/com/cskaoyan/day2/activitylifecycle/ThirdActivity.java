package com.cskaoyan.day2.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Log.i(TAG,"onCreate");

        //获取任务栈的ID
        int taskId = getTaskId();
        Log.i(TAG,"taskId="+taskId);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG,"onNewIntent");
    }

    public void jump(View v){
        startActivity(new Intent(this,SecondActivity.class));
    }

    public void jumpToSelf(View v){
        startActivity(new Intent(this,ThirdActivity.class));
    }

    public void jumpTo4(View v){
        startActivity(new Intent(this,FourthActivity.class));
    }
}
