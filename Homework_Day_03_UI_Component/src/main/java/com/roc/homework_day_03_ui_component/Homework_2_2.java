package com.roc.homework_day_03_ui_component;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homework_2_2 extends AppCompatActivity {

    public static final String MyTAG = "onCreate";

    private TextView textView;
    //private Button buttonText = (Button) findViewById(R.id.button_text);
    //private Button buttonTextColor = (Button) findViewById(R.id.button_textcolor);
    //private Button buttonTextBg = (Button) findViewById(R.id.button_bgcolor);
    //private Button buttonTextSize = (Button) findViewById(R.id.button_textsize);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);


        Intent intent = getIntent();

        //Log.d(MyTAG,"oncreate run d");
        //Toast.makeText(this,"enter homeowork2_2", Toast.LENGTH_SHORT).show();


        textView = (TextView) findViewById(R.id.text);

        Button buttonText = (Button) findViewById(R.id.button_text);
        Button buttonTextColor = (Button) findViewById(R.id.button_textcolor);
        Button buttonTextBg = (Button) findViewById(R.id.button_bgcolor);
        Button buttonTextSize = (Button) findViewById(R.id.button_textsize);

        buttonText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //((TextView) v).setText("匿名内部类");
                textView.setText("匿名内部类");
            }
        });

        buttonTextColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //((TextView) v).setTextColor(5);
                //textView.setTextColor(5);
                textView.setTextColor(Color.RED);

            }
        });

        buttonTextBg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //v.setBackgroundColor(15);
                //textView.setBackgroundColor(15);
                textView.setBackgroundColor(Color.GREEN);
            }
        });

        buttonTextSize.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //((TextView) v).setTextSize(20);
                textView.setTextSize(20);
            }
        });

    }

    public void backhome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
