package com.roc.homework_day_05_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Homework4_Evaluation extends AppCompatActivity {

    String goodsID;
    TextView evaluationET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4__evaluation);

        Intent intentE = getIntent();
        goodsID = intentE.getStringExtra("goodsID");
        evaluationET = (TextView) findViewById(R.id.Evaluation);
        evaluationET.setText(goodsID+"评价");
    }

    public void backHome(View view){
        startActivity(new Intent(this,Homework4_Home.class));
    }
}
