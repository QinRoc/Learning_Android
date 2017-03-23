package com.cskaoyan.day2.day06mybroadcastsender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadcast(View v){
        //要用上下文里面的api去发广播
        Intent  intent = new Intent();
        intent.setAction("com.cskaoyan.mybroadcast");
        intent.putExtra("key","i am some data");
        //sendBroadcast(intent);
        sendOrderedBroadcast(intent,null);
    }
}
