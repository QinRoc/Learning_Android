package com.roc.homework_day_04_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.roc.homework_day_04_activity.com.roc.domain.UserBean;

public class Homework2 extends AppCompatActivity {

     EditText usernameET;
     EditText passwordET;
     EditText repasswordET;
     EditText emailET;
     EditText phoneET;
     RadioGroup genderRG;
     RadioButton maleRB ;
     RadioButton femaleRB;


    UserBean user;

    Button register ;

    Intent intentTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);

        usernameET = (EditText) findViewById(R.id.username);
        passwordET = (EditText) findViewById(R.id.password);
        repasswordET = (EditText) findViewById(R.id.repassword);
        emailET = (EditText) findViewById(R.id.email);
        phoneET = (EditText) findViewById(R.id.phone);
        genderRG = (RadioGroup) findViewById(R.id.gender);
        maleRB = (RadioButton) findViewById(R.id.male);
        femaleRB = (RadioButton) findViewById(R.id.female);

        user = new UserBean();

        register = (Button) findViewById(R.id.UserRegister);

        intentTo = new Intent(this, Homework2_UserInfo.class);

        if(genderRG!=null){
            genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    switch(checkedId){
                        case R.id.male:
                            user.setGender(maleRB.getText().toString());
                            break;
                        case R.id.female:
                            user.setGender(femaleRB.getText().toString());
                            break;
                    }
                }
            });
        }

        if(register!=null){
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = usernameET.getText().toString();
                    String password = passwordET.getText().toString();
                    String repassword = repasswordET.getText().toString();
                    String email = emailET.getText().toString();
                    String phone = phoneET.getText().toString();

                    Boolean status = false;

                    if(username!=null){
                        user.setUsername(username);
                        status = true;
                    }else{
                        status = false;
                    }

                    if(password!=null&&repassword!=null){
                        if(password.equals(repassword)){
                            user.setPassword(password);
                            status = true;
                        }else{
                            password=null;
                            status = false;
                        }
                    }else{
                        status = false;
                    }

                    if (email!=null){
                        user.setEmail(email);
                    }else{
                        status = false;
                    }

                    if(phone!=null){
                        user.setPhone(phone);
                    }else{
                        status = false;
                    }

                    if(username!=null&&password!=null&&email!=null&&phone!=null) {

                        intentTo.putExtra("user", user);
                        startActivity(intentTo);
                    }

                }
            });
        }


    }

}
