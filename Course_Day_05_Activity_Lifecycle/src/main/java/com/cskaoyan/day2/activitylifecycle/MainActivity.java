package com.cskaoyan.day2.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity LifeCycle";

    //Activity创建的时候执行
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null){
            Log.i(TAG,"onCreate" + savedInstanceState.hashCode());
        }else {
            Log.i(TAG,"onCreate");
        }

        //获取任务栈的ID
        int taskId = getTaskId();
        Log.i(TAG,"taskId="+taskId);
    }

    //当页面显示在这个屏幕上的时候调用  （可见，不可交互）
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    //当我们的Activity onResume 可以跟用户交互的时候，会调用（可见，可交互）
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    //不可交互，但是可以看到 （可见，不可交互）
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    //已经在屏幕上看不到了 （不可见，不可交互）
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    //Activity对象没销毁的时候调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    //保存Activity里面的一些状态
    //这里传入一个空的Bundle，用于保存数据
    @Override
    protected void onSaveInstanceState(Bundle outState) {
         super.onSaveInstanceState(outState);

       /* EditText editText = (EditText) findViewById(R.id.editText);

        String s = editText.getText().toString();

        outState.putString("edittext",s );*/

        Log.i(TAG,"onSaveInstanceState" + outState.hashCode());
    }

    //恢复Activity的一些状态
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

       /* String edittext = savedInstanceState.getString("edittext");

        if (edittext!=null){
            EditText editText = (EditText) findViewById(R.id.editText);
            editText.setText(edittext);
        }*/

        Log.i(TAG,"onRestoreInstanceState"+savedInstanceState.hashCode());
    }

    public void jump(View v){
        startActivity(new Intent(this,SecondActivity.class));
    }
}
