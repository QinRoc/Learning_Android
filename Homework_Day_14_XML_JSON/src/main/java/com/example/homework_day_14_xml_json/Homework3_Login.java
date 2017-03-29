package com.example.homework_day_14_xml_json;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Homework3_Login extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i("handler", "handler");

            switch (msg.what) {
                case 1:
                    //Log.i("handler", "msg.obj:" + msg.obj);
                    Intent intent = new Intent(Homework3_Login.this, Homework3_User_Info.class);
                    //intent.putExtra("user", (Serializable) msg.obj);
                    intent.putExtra("username", (String) msg.obj);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__login);

        usernameET = (EditText) findViewById(R.id.username_Login);
        passwordET = (EditText) findViewById(R.id.password_Login);

        Intent intent = getIntent();
        String result = intent.getStringExtra("success");
        if ("success".equals(result)) {
            Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view) {

        final String username = usernameET.getText().toString();
        final String password = passwordET.getText().toString();

        if (username != null && password != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String path = "http://10.0.2.2/network/user?type=login";
                    try {
                        URL url = new URL(path);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setReadTimeout(5000);
                        connection.setConnectTimeout(5000);
                        OutputStream outputStream = connection.getOutputStream();
                        outputStream.write(("username=" + username + "&password=" + password).getBytes());
                        connection.connect();

                        int statusCode = connection.getResponseCode();
                        Log.i("login", "statusCode:" + statusCode);
                        if (statusCode == 200) {
                            InputStream inputStream = connection.getInputStream();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                                byteArrayOutputStream.write(buffer, 0, len);
                            }

                            String response = byteArrayOutputStream.toString();
                            Log.i("login", "response:" + response);

                            inputStream.close();
                            outputStream.close();

                            //JSONObject jsonObject;
                            //UserBean userBean = new UserBean();

                            Message message = new Message();

                            switch (response) {
                                case "0":
                                    Log.i("Login", "登录成功");
                                    message.what = 1;
                                    //message.obj = userBean;
                                    break;
                                case "1":
                                    Log.i("Login", "密码错误");
                                    break;
                                case "-1":
                                    Log.i("Login", "没有该用户");
                                    break;
                                /*default:
                                    jsonObject = new JSONObject(response);
                                    Log.i("Login", "jsonObject:" + jsonObject);

                                    userBean.setUsername(jsonObject.getString("username"));
                                    userBean.setPassword(jsonObject.getString("password"));
                                    userBean.setEmail(jsonObject.getString("email"));
                                    userBean.setPhone(jsonObject.getString("phone"));
                                    userBean.setGender(jsonObject.getString("gender"));
                                    Log.i("Login", "userBean:" + userBean);
                                    break;*/
                            }

                            message.what = 1;
                            //message.obj = userBean;
                            //Log.i("Login", "msg.obj:" + message.obj);
                            message.obj = username;
                            handler.sendMessage(message);
                        } else {
                            Log.i("Login", "服务器无响应");
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } /*catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                }
            }).start();
        }
    }

    public void register(View view) {
        startActivity(new Intent(this, Homework3_Register.class));
    }
}
