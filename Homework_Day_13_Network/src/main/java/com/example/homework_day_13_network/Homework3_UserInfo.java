package com.example.homework_day_13_network;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.homework_day_13_network.domain.UserBean;

public class Homework3_UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__user_info);

        Intent intent = getIntent();
        UserBean userBean = (UserBean) intent.getSerializableExtra("user");
        TextView textView = new TextView(this);
        textView.setText(userBean.toString());
        ConstraintLayout layout = (ConstraintLayout) this.findViewById(R.id.layout);
        layout.addView(textView);
    }
}
