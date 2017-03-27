package com.cskaoyan.week3.registerdemoapp;

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

    public void register(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void shouUserInfo(View v) {
        startActivity(new Intent(this, ShowUserActivity.class));
    }
}
