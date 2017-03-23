package com.roc.homework_day_05_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homework4_Detail extends AppCompatActivity {

    String goodsID;
    TextView detailsET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4__detail);

        Intent intentGet = getIntent();
        goodsID = intentGet.getStringExtra("goodsID");

        detailsET = (TextView) findViewById(R.id.Detail);
        detailsET.setText(goodsID);
    }

    public void evaluate(View view){
        Intent intent = new Intent(this,Homework4_Evaluation.class);
        intent.putExtra("goodsID",goodsID);
        startActivity(intent);
    }

    public void goodsDetail(View view){
        //view = (Button) view;
        Intent intent = new Intent(this,Homework4_Detail.class);
        intent.putExtra("goodsID",((Button) view).getText());
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        setIntent(intent);
        Intent intentGet = getIntent();
        goodsID = intentGet.getStringExtra("goodsID");
        detailsET = (TextView) findViewById(R.id.Detail);
        detailsET.setText(goodsID);
    }
}
