package com.example.homework_day_13_network;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.homework_day_13_network.domain.UserBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Homework2_Register extends AppCompatActivity {

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
        setContentView(R.layout.activity_homework2__register);

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

        user.setGender("m");
        if (genderRG != null) {
            genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    switch (checkedId) {
                        case R.id.male:
                            //user.setGender(maleRB.getText().toString());
                            user.setGender("m");
                            break;
                        case R.id.female:
                            //user.setGender(femaleRB.getText().toString());
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

                    Boolean status = false;

                    if (username != null) {
                        user.setUsername(username);
                        status = true;
                    } else {
                        status = false;
                    }

                    if (password != null && repassword != null) {
                        if (password.equals(repassword)) {
                            user.setPassword(password);
                            status = true;
                        } else {
                            password = null;
                            status = false;
                        }
                    } else {
                        status = false;
                    }

                    if (email != null) {
                        user.setEmail(email);
                    } else {
                        status = false;
                    }

                    if (phone != null) {
                        user.setPhone(phone);
                    } else {
                        status = false;
                    }

                    if (username != null && password != null && email != null && phone != null) {

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
                                        /*while ((len = inputStream.read(buffer)) != -1) {
                                            byteArrayOutputStream.write(buffer);
                                        }*/
                                        while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                                            byteArrayOutputStream.write(buffer, 0, len);
                                        }

                                        String response = byteArrayOutputStream.toString();
                                        Log.i("register", "response:" + response);

                                        inputStream.close();
                                        outputStream.close();
                                        switch (response) {
                                            case "0":
                                                //Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                Log.i("Register", "注册成功");
                                                break;
                                            case "1":
                                                //Toast.makeText(MainActivity.this, "已经存在同名/同手机号/同邮箱用户", Toast.LENGTH_SHORT).show();
                                                Log.i("Register", "已经存在同名/同手机号/同邮箱用户");
                                                break;
                                            case "-1":
                                                //Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                                Log.i("Register", "注册失败");
                                                break;
                                            default:
                                                break;
                                        }
                                    } else {
                                        //Toast.makeText(MainActivity.this, "服务器无响应", Toast.LENGTH_SHORT).show();
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
                        //Toast.makeText(MainActivity.this, "有的资料未填写", Toast.LENGTH_SHORT).show();
                        Log.i("register", "有的资料未填写");
                    }
                }
            });
        }
    }
}
