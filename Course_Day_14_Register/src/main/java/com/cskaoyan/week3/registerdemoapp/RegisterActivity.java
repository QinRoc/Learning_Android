package com.cskaoyan.week3.registerdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cskaoyan.week3.registerdemoapp.Bean.User;
import com.cskaoyan.week3.registerdemoapp.utils.HttpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {


    public static final int MSG_OK = 1;
    public static final int MSG_EORRO = 0;
    public static final String TAG = "RegisterActivity";
    String gender = "男";
    Handler myHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_EORRO:
                    break;
                case MSG_OK:
                    Toast.makeText(RegisterActivity.this, "register OK!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    break;
            }
        }
    };
    private EditText username_et;
    private EditText password_et;
    private EditText email_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username_et = (EditText) findViewById(R.id.editText);
        password_et = (EditText) findViewById(R.id.editText2);
        email_et = (EditText) findViewById(R.id.editText3);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.radio_female:
                        gender = "女";
                        break;
                    case R.id.radio_male:
                        gender = "男";
                        break;
                }
            }
        });
    }

    public void submit(View v) {

        String username = username_et.getText().toString();
        String password = password_et.getText().toString();
        String email = email_et.getText().toString();

        User user = new User(0, username, password, email, gender);

        sendToServer(user);
    }

    private void sendToServer(final User user) {

        new Thread(new Runnable() {


            @Override
            public void run() {

                String path = "http://10.0.2.2/ServerAPIProject/servlet/RegisterServlet";
                Log.i(TAG, "user=" + user.toString());
                try {
                    URL url = new URL(path);

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestProperty("User-Agent", "android");
                    urlConnection.setRequestMethod("POST");

                    OutputStream outputStream = urlConnection.getOutputStream();
                    String body = "username=" + user.getUsername() +
                            "&password=" + user.getPassword() +
                            "&email=" + user.getEmail() +
                            "&gender=" + user.getGender();

                    outputStream.write(body.getBytes());

                    urlConnection.connect();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {

                        InputStream inputStream = urlConnection.getInputStream();

                        String stringFromInputstream = HttpUtils.getStringFromInputstream(inputStream);

                        if ("success".equals(stringFromInputstream)) {
                            Message msg = new Message();
                            msg.what = MSG_OK;
                            myHander.sendMessage(msg);

                        } else {
                            Message msg = new Message();
                            msg.what = MSG_EORRO;
                            myHander.sendMessage(msg);
                        }
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
        }).start();
    }
}
