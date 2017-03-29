package com.example.homework_day_14_xml_json;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import com.example.homework_day_14_xml_json.domain.UserBean;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Homework3_User_Info extends AppCompatActivity {

    private String username;
    private TextView welcomeTV;
    private TextView userInfoTV;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            UserBean user = (UserBean) msg.obj;

            userInfoTV.setText(user.toString());
            //userInfoTV.setText(msg.obj.toString());
            //userInfoTV.setText(user);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__user__info);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        welcomeTV = (TextView) findViewById(R.id.welcome);
        welcomeTV.setText("欢迎" + username);

        userInfoTV = (TextView) findViewById(R.id.userinfo);
    }

    public void inquireUserInfoXML(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String path = "http://10.0.2.2/network/user?type=inquire&data=xml";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(("username=" + username).getBytes());
                    connection.connect();

                    int statusCode = connection.getResponseCode();
                    Log.i("inquireUserInfoXML", "statusCode:" + statusCode);
                    if (statusCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        XmlPullParser parser = Xml.newPullParser();
                        parser.setInput(inputStream, "utf-8");

                        UserBean userBean = null;

                        int next = parser.next();
                        Log.i("inquireUserInfoXML", "next:" + next);
                        while (next != XmlPullParser.END_DOCUMENT) {
                            if (next == XmlPullParser.START_TAG) {
                                String name = parser.getName();
                                Log.i("inquireUserInfoXML", "name:" + name);
                                switch (name) {
                                    case "userBean":
                                        userBean = new UserBean();
                                        break;
                                    case "username":
                                        userBean.setUsername(parser.nextText());
                                        break;
                                    case "password":
                                        userBean.setPassword(parser.nextText());
                                        break;
                                    case "email":
                                        userBean.setEmail(parser.nextText());
                                        break;
                                    case "phone":
                                        userBean.setPhone(parser.nextText());
                                        break;
                                    case "gender":
                                        userBean.setGender(parser.nextText());
                                        break;
                                }
                            }
                            next = parser.next();
                        }

                        inputStream.close();
                        Message message = new Message();
                        message.what = 1;
                        message.obj = userBean;
                        handler.sendMessage(message);
                    } else {
                        Log.i("inquireUserInfoXML", "服务器无响应");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void inquireUserInfoJSON(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.0.2.2/network/user?type=inquire&data=json";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(("username=" + username).getBytes());
                    connection.connect();

                    int statusCode = connection.getResponseCode();
                    Log.i("inquireUserInfoJSON", "statusCode:" + statusCode);
                    if (statusCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                            byteArrayOutputStream.write(buffer, 0, len);
                        }

                        String response = byteArrayOutputStream.toString();
                        Log.i("inquireUserInfoJSON", "response:" + response);

                        inputStream.close();
                        outputStream.close();

                        JSONObject jsonObject = new JSONObject(response);
                        Log.i("inquireUserInfoJSON", "jsonObject:" + jsonObject);

                        UserBean userBean = new UserBean();
                        userBean.setUsername(jsonObject.getString("username"));
                        userBean.setPassword(jsonObject.getString("password"));
                        userBean.setEmail(jsonObject.getString("email"));
                        userBean.setPhone(jsonObject.getString("phone"));
                        userBean.setGender(jsonObject.getString("gender"));

                        Message message = new Message();
                        message.what = 2;
                        message.obj = userBean;
                        //Log.i("Login", "msg.obj:" + message.obj);
                        message.obj = userBean;
                        handler.sendMessage(message);
                    } else {
                        Log.i("inquireUserInfoJSON", "服务器无响应");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
