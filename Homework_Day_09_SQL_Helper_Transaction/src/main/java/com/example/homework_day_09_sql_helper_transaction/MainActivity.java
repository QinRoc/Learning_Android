package com.example.homework_day_09_sql_helper_transaction;

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

    public void homework2(View view) {
        startActivity(new Intent(this, Homework2_Save_Keywords.class));
    }

    public void homework3(View view) {
        startActivity(new Intent(this, Homework3_Database_Upgrade.class));
    }

    public void homework4(View view) {
        startActivity(new Intent(this, Homework4_Review_SQL.class));
    }
}
