package com.cskaoyan.week2.listviewdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class SecondActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);

        //要显示某个同学的具体信息。
        Intent intent = getIntent();
        Student stu = intent.getParcelableExtra("stu");

        //Resources$NotFoundException: String resource ID #0x8
        //这里需要我们传入字符串，否则会调用到另一API。
        editText.setText(stu.getId() + "");
        editText2.setText(stu.getName());
        editText3.setText(stu.getAge() + "");
    }
}
