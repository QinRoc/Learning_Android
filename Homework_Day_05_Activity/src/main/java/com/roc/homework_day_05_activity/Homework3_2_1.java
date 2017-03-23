package com.roc.homework_day_05_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Homework3_2_1 extends AppCompatActivity {

    private String TAG = "LifeCycle: To";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3_2_1);

        Log.i(TAG,"onCreate");
    }

    public void to(View view){
        startActivity(new Intent(this,Homework3_2_2.class));
    }

    /*
    void onCreate()
    void onStart() 可见, 不可交互
    void onRestart()
    void onResume() 可见, 可交互
    void onPause()
    void onStop()
    void onDestroy()
    */

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
