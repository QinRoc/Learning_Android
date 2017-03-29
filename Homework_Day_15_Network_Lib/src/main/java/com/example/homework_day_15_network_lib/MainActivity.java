package com.example.homework_day_15_network_lib;

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

    public void inquireID(View view) {
        startActivity(new Intent(this, Homework2_inquireID.class));
    }

    public void asynchronousTask(View view) {
        startActivity(new Intent(this, Homework3_Asynchronous_Task.class));
    }

    public void uploadPhoto(View view) {
        startActivity(new Intent(this, Homework4_Upload_Photo.class));
    }


}
