package com.cskaoyan.day2.activitylifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class FourthActivity extends AppCompatActivity {

    private static final String TAG = "FourthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Log.i(TAG,"onCreate");

        //获取任务栈的ID
        int taskId = getTaskId();
        Log.i(TAG,"taskId="+taskId);
    }
}
