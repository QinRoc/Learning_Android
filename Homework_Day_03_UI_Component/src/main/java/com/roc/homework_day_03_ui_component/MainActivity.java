package com.roc.homework_day_03_ui_component;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String MyTAG = "onCreate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(MyTAG,"oncreate run d");
        //Toast.makeText(this,"enter homeowork2", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
    }

    public void homework2_1(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Homework_2_1.class);
        startActivity(intent);
    }

    public void homework2_2(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Homework_2_2.class);
        startActivity(intent);
    }

    public void homework2_3(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Homework_2_3.class);
        startActivity(intent);
    }

    public void homework2_4(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Homework_2_4.class);
        startActivity(intent);
    }

    public void homework4(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Homework_4.class);
        startActivity(intent);
    }
}
