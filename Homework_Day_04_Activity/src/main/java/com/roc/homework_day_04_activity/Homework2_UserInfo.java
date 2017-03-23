package com.roc.homework_day_04_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roc.homework_day_04_activity.com.roc.domain.UserBean;

public class Homework2_UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2__user_info);
        Intent intent = getIntent();
        UserBean user = (UserBean) intent.getSerializableExtra("user");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(user.toString());

        ViewGroup layout = (ViewGroup) findViewById(R.id.userinfo);
        layout.addView(textView);
    }
}
