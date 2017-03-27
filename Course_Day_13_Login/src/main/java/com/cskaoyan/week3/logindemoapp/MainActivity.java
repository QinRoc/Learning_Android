package com.cskaoyan.week3.logindemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText username_et;
    private EditText password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_et = (EditText) findViewById(R.id.editText);
        password_et = (EditText) findViewById(R.id.editText2);
    }

    //使用GET请求登录
    public void login_get(View v) {

        final String username = username_et.getText().toString();
        final String password = password_et.getText().toString();

        //android端也要用正则去过滤一些不合法的数据。避免频繁访问服务器
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {

            //继续增加正则判断
            //boolean matches = username.matches("(/n)+");
            //boolean matches = password.matches("(/n)+");

            //把用户和密码放在一个http请求里，发给服务器验证

            //启动子线程，去做联网的耗时操作
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String path = "http://10.0.2.2/MyAppServerProject/servlet/LoginServlet?username=" + username
                            + "&password=" + password;

                    try {
                        URL url = new URL(path);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                        //通过get
                        //数据放在那里？ 通过get方法去发送的数据，数据放在请求行里 就是url后面
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setConnectTimeout(5000);
                        urlConnection.setReadTimeout(5000);

                        urlConnection.connect();

                        int responseCode = urlConnection.getResponseCode();

                        if (responseCode == 200) { //200表示服务器正常返回数据，而非登录成功

                            //拿到一个输入流。就是负责把响应报文里的内容给到你
                            InputStream inputStream = urlConnection.getInputStream();

                            //ByteArrayOutputStream 内存的输出流  输出到内存的一个buffer（字节数组）
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();

                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                                baos.write(buffer, 0, len);
                            }

                            String response = baos.toString();

                            inputStream.close();
                            baos.close();

                            if (response.equals("success")) {
                                Log.i(TAG, "登录成功");
                            } else {
                                Log.i(TAG, "登录失败，用户名或者密码错误！");
                            }
                        } else {
                            Log.i(TAG, "请求失败");
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

    //或者post请求来登录
    public void login_post(View v) {

        final String username = username_et.getText().toString();
        final String password = password_et.getText().toString();

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    String path = "http://10.0.2.2/MyAppServerProject/servlet/LoginServlet";

                    try {
                        URL url = new URL(path);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        conn.setRequestMethod("POST");
                        conn.setConnectTimeout(5000);
                        conn.setReadTimeout(1000);

                        //post提交数据，数据放在请求的正文里,通过一个它提供的输出流，输出到正文
                        OutputStream outputStream = conn.getOutputStream();

                        String body = "username=" + username + "&password=" + password;
                        //"username=allen&password=123"
                        outputStream.write(body.getBytes());

                        conn.connect();

                        int responseCode = conn.getResponseCode();

                        if (responseCode == 200) {
                            //拿到一个输入流。就是负责把响应报文里的内容给到你
                            InputStream inputStream = conn.getInputStream();

                            //ByteArrayOutputStream 内存的输出流  输出到内存的一个buffer（字节数组）
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();

                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                                baos.write(buffer, 0, len);
                            }

                            String response = baos.toString();

                            inputStream.close();
                            baos.close();

                            if (response.equals("success")) {
                                Log.i(TAG, "登录成功");
                            } else {
                                Log.i(TAG, "登录失败，用户名或者密码错误！");
                            }
                        } else {
                            Log.i(TAG, "连接失败");
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
}
