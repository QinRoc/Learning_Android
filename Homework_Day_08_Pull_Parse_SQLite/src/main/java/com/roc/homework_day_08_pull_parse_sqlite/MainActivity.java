package com.roc.homework_day_08_pull_parse_sqlite;

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

    public void homework2(View view){
        startActivity(new Intent(this,Homework2_Pull_Parse.class));
    }

    public void homework3(View view){
        startActivity(new Intent(this, Homework3_SQLite.class));
    }
}
