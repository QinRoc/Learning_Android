package com.example.homework_day_11_content_accesser;

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
        startActivity(new Intent(this, Homework2.class));
    }

    public void homework3(View view) {
        startActivity(new Intent(this, Homework3_Backup_Contacts.class));
    }
}
