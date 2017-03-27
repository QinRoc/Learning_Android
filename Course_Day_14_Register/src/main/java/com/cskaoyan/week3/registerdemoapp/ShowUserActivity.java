package com.cskaoyan.week3.registerdemoapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.widget.EditText;
import android.widget.RadioButton;

import com.cskaoyan.week3.registerdemoapp.Bean.User;
import com.cskaoyan.week3.registerdemoapp.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ShowUserActivity extends AppCompatActivity {

    private static final String TAG = "ShowUserActivity";
    private static final int MSG_EORRO = 0;
    private static final int MSG_OK = 1;
    private EditText username_et;
    private EditText password_et;
    private EditText email_et;
    private RadioButton radio_female;
    private RadioButton radio_male;
    Handler myHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_EORRO:
                    break;
                case MSG_OK:

                    JSONObject user = (JSONObject) msg.obj;

                    try {
                        username_et.setText(user.getString("username"));
                        password_et.setText(user.getString("password"));
                        email_et.setText(user.getString("email"));
                        if (user.getString("gender").equals("男")) {
                            radio_male.setChecked(true);
                        } else {
                            radio_female.setChecked(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    /*username_et.setText(user.getUsername());
                    password_et.setText(user.getPassword());
                    email_et.setText(user.getEmail());
                    if (user.getGender().equals("男")){
                        radio_male.setChecked(true);
                    }else
                    {
                        radio_female.setChecked(true);
                    }*/

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        username_et = (EditText) findViewById(R.id.editText);
        password_et = (EditText) findViewById(R.id.editText2);
        email_et = (EditText) findViewById(R.id.editText3);
        radio_female = (RadioButton) findViewById(R.id.radio_female);
        radio_male = (RadioButton) findViewById(R.id.radio_male);

        getUserDataFromSever();
    }

    private void getUserDataFromSever() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String path = "http://10.0.2.2/ServerAPIProject/showUsersInfo?id=1";

                try {
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("User-Agent", "android");
                    urlConnection.connect();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {

                        InputStream inputStream = urlConnection.getInputStream();

                        //String stringFromInputstream = HttpUtils.getStringFromInputstream(inputStream);

                        //Log.i(TAG,stringFromInputstream);

                        //User user = parseXmlData(inputStream);
                        JSONObject user = parseJsonData(inputStream);

                        Message msg = new Message();
                        msg.what = MSG_OK;
                        msg.obj = user;
                        myHander.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = MSG_EORRO;
                        myHander.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private JSONObject parseJsonData(InputStream inputStream) {

                String stringFromInputstream = HttpUtils.getStringFromInputstream(inputStream);

                Log.i(TAG, stringFromInputstream);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(stringFromInputstream);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return jsonObject;
            }

            private User parseXmlData(InputStream inputStream) {

                User user = null;

                XmlPullParser parser = Xml.newPullParser();

                try {
                    parser.setInput(inputStream, "utf-8");

                    int next = parser.next();
                    while (next != XmlPullParser.END_DOCUMENT) {

                        if (next == XmlPullParser.START_TAG) {

                            String name = parser.getName();
                            switch (name) {
                                case "user":
                                    user = new User();
                                    break;
                                case "username":
                                    user.setUsername(parser.nextText());
                                    break;
                                case "password":
                                    user.setPassword(parser.nextText());
                                    break;
                                case "email":
                                    user.setEmail(parser.nextText());
                                    break;
                                case "gender":
                                    user.setGender(parser.nextText());
                                    break;
                            }
                        }

                        next = parser.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return user;
            }
        }).start();
    }
}
