package com.roc.homework_day_05_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Homework4_Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4__home);
    }

    public void goodsDetail(View view){
        //view = (Button) view;
        Intent intent = new Intent(this,Homework4_Detail.class);
        intent.putExtra("goodsID",((Button) view).getText());
        startActivity(intent);
    }
}
