package com.roc.homework_day_04_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Main","oncreate run d");
    }

    public void homework2(View view) {
        Intent intent = new Intent(this, Homework2.class);
        startActivity(intent);
    }

    public void homework3(View view) {
        Intent intent = new Intent(this, Homework3.class);
        startActivity(intent);
    }

    public void homework4(View view) {
        Intent intent = new Intent(this, Homework4.class);
        startActivity(intent);
    }


}
