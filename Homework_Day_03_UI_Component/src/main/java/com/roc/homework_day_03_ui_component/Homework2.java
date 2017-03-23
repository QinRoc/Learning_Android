package com.roc.homework_day_03_ui_component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Homework2 extends AppCompatActivity {

    //private TextView textView = (TextView) findViewById(R.id.text);
    private TextView textView;
    //private Button buttonText = (Button) findViewById(R.id.button_text);
    //private Button buttonTextColor = (Button) findViewById(R.id.button_textcolor);
    //private Button buttonTextBg = (Button) findViewById(R.id.button_bgcolor);
    //private Button buttonTextSize = (Button) findViewById(R.id.button_textsize);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);

        textView = (TextView) findViewById(R.id.text);
    }

    public void setText(View v){
        //v = (TextView)v;
        ((TextView) v).setText("默认内容");
    }

    public void setTextColor(View v){
        //v = (TextView)v;
        ((TextView) v).setTextColor(5);
    }

    public void setTextSize(View v){
        //v = (TextView)v;
        ((TextView) v).setTextSize(30);
    }

    public void setTextBgColor(View v){
        //v = (TextView)v;
        //((TextView) v).setBackgroundColor(15);
         v.setBackgroundColor(15);
    }

}
