package com.roc.homework_day_03_ui_component;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homework_2_4 extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;

    /*private Button buttonText = (Button) findViewById(R.id.button_text);
    private Button buttonTextColor = (Button) findViewById(R.id.button_textcolor);
    private Button buttonTextBg = (Button) findViewById(R.id.button_bgcolor);
    private Button buttonTextSize = (Button) findViewById(R.id.button_textsize);*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);

        Intent intent = getIntent();
        textView = (TextView) findViewById(R.id.text);
        Button buttonText = (Button) findViewById(R.id.button_text);
        Button buttonTextColor = (Button) findViewById(R.id.button_textcolor);
        Button buttonTextBg = (Button) findViewById(R.id.button_bgcolor);
        Button buttonTextSize = (Button) findViewById(R.id.button_textsize);

        buttonText.setOnClickListener(this);
        buttonTextColor.setOnClickListener(this);
        buttonTextBg.setOnClickListener(this);
        buttonTextSize.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_text:
                //((TextView) v).setText("Activity继承OnClickListener");
                textView.setText("Activity继承OnClickListener");
                break;

            case R.id.button_textcolor:
                //((TextView) v).setTextColor(5);
                //textView.setTextColor(50);
                textView.setTextColor(Color.RED);
                break;

            case R.id.button_textsize:
                //((TextView) v).setTextSize(20);
                textView.setTextSize(40);
                break;

            case R.id.button_bgcolor:
                //v.setBackgroundColor(15);
                //textView.setBackgroundColor(99);
                textView.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public void backhome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
