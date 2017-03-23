package com.roc.homework_day_07_file_system;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Homework2(View view){
        startActivity(new Intent(this,Homework2_Register.class));
    }

    public void Homework3(View view){
        startActivity(new Intent(this,Homework3.class));
    }

    public void Homework4(View view){
        String externalStorageState = Environment.getExternalStorageState();

        Log.i("SD Card State",externalStorageState);
        Toast.makeText(this, externalStorageState, Toast.LENGTH_SHORT).show();


    }

}
