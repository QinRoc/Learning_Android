package com.roc.homework_day_05_activity;

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

    public void homework2_1(View view){
        startActivity(new Intent(this,Homework2_1.class));
    }

    public void homework2_2(View view){
        startActivity(new Intent(this,Homework2_2.class));
    }

    public void homework3_1(View view){
        startActivity(new Intent(this,Homework3_1.class));
    }

    public void homework3_2_1(View view){
        startActivity(new Intent(this,Homework3_2_1.class));
    }

    public void homework4_Home(View view){
        startActivity(new Intent(this,Homework4_Home.class));
    }

    public void donate(View view){
        Intent intent = new Intent();
        intent.setAction("");
        intent.addCategory("");
        startActivity(intent);
    }
}
