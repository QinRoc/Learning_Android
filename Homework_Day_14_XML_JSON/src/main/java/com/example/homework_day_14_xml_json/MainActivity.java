package com.example.homework_day_14_xml_json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void homework2(View view) {
        startActivity(new Intent(this, Homework2_Number_Location.class));
    }

    public void homework3(View view) {
        startActivity(new Intent(this, Homework3_Login.class));
    }
}
