package com.example.homework_day_10_listview_recycleview;

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
        startActivity(new Intent(this, Homework2_ListView.class));
    }

    public void homework3_StarsList(View view) {
        startActivity(new Intent(this, Homework3_RecycleView_StarsList.class));
    }

    public void homework3_StaggeerGrid(View view) {
        startActivity(new Intent(this, Homework3_RecycleView_StaggeerGridLayoutManager.class));
    }

}
