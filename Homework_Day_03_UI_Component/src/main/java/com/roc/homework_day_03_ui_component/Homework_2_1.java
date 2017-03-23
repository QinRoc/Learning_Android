package com.roc.homework_day_03_ui_component;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Homework_2_1 extends AppCompatActivity {

    public static final String MyTAG = "onCreate";

    //private TextView textView = (TextView) findViewById(R.id.text);
    private TextView textView;
    //private Button buttonText = (Button) findViewById(R.id.button_text);
    //private Button buttonTextColor = (Button) findViewById(R.id.button_textcolor);
    //private Button buttonTextBg = (Button) findViewById(R.id.button_bgcolor);
    //private Button buttonTextSize = (Button) findViewById(R.id.button_textsize);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_2_1);

        //Log.d(MyTAG,"oncreate run d");
        //Toast.makeText(this,"enter homeowork2",Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();

        textView = (TextView) findViewById(R.id.text);
    }

    public void backhome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    /*public void setText(View v){
        ((TextView) v).setText("默认内容");
    }

    public void setTextColor(View v){
        ((TextView) v).setTextColor(5);
    }

    public void setTextSize(View v){
        ((TextView) v).setTextSize(20);
    }

    public void setTextBgColor(View v){
        ((TextView) v).setBackgroundColor(15);
    }*/

    public void setTextView(View v){
        switch(v.getId()){
            case R.id.button_text:
                //((TextView) v).setText("xml设置属性");
                textView.setText("xml设置属性");
                break;

            case R.id.button_textcolor:
                //((TextView) v).setTextColor(5);
                //textView.setTextColor(80);
                textView.setTextColor(Color.RED);
                break;

            case R.id.button_textsize:
                //((TextView) v).setTextSize(20);
                textView.setTextSize(20);
                break;

            case R.id.button_bgcolor:
                //v.setBackgroundColor(15);
                //textView.setBackgroundColor(66);
                textView.setBackgroundColor(Color.GREEN);
                break;
        }

    }

}
