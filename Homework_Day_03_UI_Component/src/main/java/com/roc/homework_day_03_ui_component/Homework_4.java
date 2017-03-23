package com.roc.homework_day_03_ui_component;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.roc.homework_day_03_ui_component.domain.UserBean;

/**
 * Created by Peng on 2017/3/15.
 */

public class Homework_4 extends AppCompatActivity{

    public static final String MyTAG = "onCreate";

    private EditText usernameET;
    private EditText passwordET;
    private EditText repasswordET;
    private EditText emailET;
    private RadioGroup genderRG;
    private RadioButton maleRB;
    private RadioButton femaleRB;
    private EditText phoneET;
    private Button register;

    private UserBean user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4);

        Intent intent = getIntent();

        usernameET = (EditText) findViewById(R.id.username);
        passwordET = (EditText) findViewById(R.id.password);
        repasswordET = (EditText) findViewById(R.id.repassword);
        emailET = (EditText) findViewById(R.id.email);
        genderRG = (RadioGroup) findViewById(R.id.gender);
        maleRB = (RadioButton) findViewById(R.id.male);
        femaleRB = (RadioButton) findViewById(R.id.female);
        phoneET = (EditText) findViewById(R.id.phone);

        register = (Button) findViewById(R.id.register);

        user = new UserBean();



        if(genderRG!=null){
            genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    //switch(genderRG.getId()){
                    switch(checkedId){
                            case R.id.male:
                                user.setGender(maleRB.getText().toString());
                                //user.setGender("男");
                                //gender = maleRB.getText().toString();
                                break;
                            case R.id.female:
                                //user.setGender("女");
                                user.setGender(femaleRB.getText().toString());
                                //gender = femaleRB.getText().toString();
                                break;
                    }

                }
            });
        }


        //Toast.makeText(Homework_4.this,user.toString(),Toast.LENGTH_LONG).show();


        if(register!=null){
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = usernameET.getText().toString();
                    String password = passwordET.getText().toString();
                    String repassword = repasswordET.getText().toString();
                    String email = emailET.getText().toString();
                    String phone = phoneET.getText().toString();
                    //String gender = "";

                    if(password.equals(repassword)){
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setEmail(email);
                        user.setPhone(phone);
                        Toast.makeText(Homework_4.this,user.toString(),Toast.LENGTH_LONG).show();
                        Log.d(MyTAG,user.toString());
                    }else{
                        Toast.makeText(Homework_4.this,"两次输入的密码需要相同！",Toast.LENGTH_SHORT).show();
                        Log.d(MyTAG,"两次输入的密码需要相同！");
                    }



                }
            });
        }

    }

    public void backhome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
