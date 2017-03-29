package com.example.homework_day_14_xml_json;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.homework_day_14_xml_json.domain.UserBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Homework3_Register extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;
    EditText repasswordET;
    EditText emailET;
    EditText phoneET;

    RadioGroup genderRG;
    RadioButton maleRB;
    RadioButton femaleRB;

    UserBean user;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__register);

        usernameET = (EditText) findViewById(R.id.username);
        passwordET = (EditText) findViewById(R.id.password);
        repasswordET = (EditText) findViewById(R.id.repassword);
        emailET = (EditText) findViewById(R.id.email);
        phoneET = (EditText) findViewById(R.id.phone);

        genderRG = (RadioGroup) findViewById(R.id.gender);
        maleRB = (RadioButton) findViewById(R.id.male);
        femaleRB = (RadioButton) findViewById(R.id.female);

        register = (Button) findViewById(R.id.UserRegister);

        user = new UserBean();

        user.setGender("m");
        if (genderRG != null) {
            genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    switch (checkedId) {
                        case R.id.male:
                            user.setGender("m");
                            break;
                        case R.id.female:
                            user.setGender("f");
                            break;
                    }
                }
            });
        }

        if (register != null) {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = usernameET.getText().toString();
                    String password = passwordET.getText().toString();
                    String repassword = repasswordET.getText().toString();
                    String email = emailET.getText().toString();
                    String phone = phoneET.getText().toString();


                    if (username != null) {
                        user.setUsername(username);
                    }

                    if (password != null && repassword != null) {
                        if (password.equals(repassword)) {
                            user.setPassword(password);
                        } else {
                            password = null;
                        }
                    }

                    if (email != null) {
                        user.setEmail(email);
                    }

                    if (phone != null) {
                        user.setPhone(phone);
                    }

                    if (username != null && !"".equals(username)
                            && password != null && !"".equals(password)
                            && email != null && !"".equals(email)
                            && phone != null && !"".equals(phone)) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String path = "http://10.0.2.2/network/user?type=register";
                                try {
                                    URL url = new URL(path);
                                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                    connection.setRequestMethod("POST");
                                    connection.setReadTimeout(5000);
                                    connection.setConnectTimeout(5000);
                                    OutputStream outputStream = connection.getOutputStream();
                                    outputStream.write(user.toString().getBytes());
                                    connection.connect();

                                    int statusCode = connection.getResponseCode();
                                    Log.i("register", "statusCode:" + statusCode);

                                    if (statusCode == 200) {
                                        InputStream inputStream = connection.getInputStream();
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        byte[] buffer = new byte[1024];
                                        int len = -1;
                                        while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                                            byteArrayOutputStream.write(buffer, 0, len);
                                        }

                                        String response = byteArrayOutputStream.toString();
                                        Log.i("register", "response:" + response);

                                        inputStream.close();
                                        outputStream.close();

                                        switch (response) {
                                            case "0":
                                                Log.i("Register", "注册成功");
                                                //Toast.makeText(Homework3_Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Homework3_Register.this, Homework3_Login.class);
                                                intent.putExtra("success", "success");
                                                startActivity(intent);
                                                break;
                                            case "1":
                                                Log.i("Register", "已经存在同名/同手机号/同邮箱用户");
                                                break;
                                            case "-1":
                                                Log.i("Register", "注册失败");
                                                break;
                                            default:
                                                break;
                                        }
                                    } else {
                                        Log.i("Register", "服务器无响应");
                                    }
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } else {
                        Log.i("register", "有的资料未填写");
                    }
                }
            });
        }
    }
}
