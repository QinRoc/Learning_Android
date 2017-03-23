package com.cskaoyan.day2.otherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "otherapplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumptoOtherApp(View v){

        Intent intent = new Intent();
        intent.setAction("com.cskaoyan.home");
        intent.addCategory("com.cskaoyan.lan");
        Log.v(TAG,"jumptoOtherApp");
        startActivity(intent);
    }
}
