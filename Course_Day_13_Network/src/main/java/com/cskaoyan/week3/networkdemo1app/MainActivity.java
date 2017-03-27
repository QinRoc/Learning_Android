package com.cskaoyan.week3.networkdemo1app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cskaoyan.week3.networkdemo1app.util.HttpUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView content;
    //自己创建一个新的消息处理器。重写其handleMessage。这个方法就是处理消息的方法。
    Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String htmlsource = (String) msg.obj;
            Log.i(TAG, "received msg!" + msg);
            content.setText(htmlsource);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = (TextView) findViewById(R.id.content);
    }

    // Caused by: android.os.NetworkOnMainThreadException
    // 网络操作在主线程
    // 可能会阻塞主线程

    public void sendRequest(View v) {

        //要请求服务器的某个资源
        //发送HTTP请求

        //之前在web开发的时候，客户端是浏览器，所以对于你邀请求的资源
        //你只需要把请求地址url给到浏览器，浏览器帮你构建http请求。
        //而且对于服务器发挥了的响应，浏览器帮你去解析了。数据帮你显示在浏览界面上。

        //现在是app开发。没有浏览器帮你构建。
        //要自己去构建。

        //Android提供了构建http协议的一个类库，可以通过其来构建http请求。
        //HttpUrlConnection
        //Java语言提供的处理HTTP请求和响应的工具类
        //可以用来封装HTTP Request，接收HTTP Response

        //http://localhost/MyAppServerProject/hello.txt

        //第二种方法
       /* new Thread(new Runnable() {
            @Override
            public void run() {

                String path = "http://localhost/MyAppServerProject/hello.txt";

                try {
                    URL url = new URL(path);

                    //URLConnection urlConnection = url.openConnection();
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    //发请求之前，要把请求的一些参数配置好
                    connection.setRequestMethod("GET");
                    //连接超时时间
                    connection.setConnectTimeout(5000);
                    //读取数据的超时时间
                    connection.setReadTimeout(1000);

                    //举例，可以通过如下api去设置请求头
                    connection.setRequestProperty("Accept-Charset","utf-8");

                    //去连接 同步的api
                    connection.connect();  //发送完请求会阻塞，知道结果回来。

                    //响应码从响应报文）
                    int responseCode = connection.getResponseCode();

                    if (responseCode==200){
                        Log.i(TAG,"response ok!");
                    }else{
                        Log.i(TAG,"response not ok! =" +responseCode);
                    }

                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        //第一种方法
        MyThread t1 = new MyThread();
        t1.start();
    }

    public void getHtml(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String path = "http://10.0.2.2/MyAppServerProject/MyHtml.html";

                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    conn.connect();

                    int responseCode = conn.getResponseCode();

                    if (responseCode == 200) {

                        String stringFromInpustStream = HttpUtils.getStringFromInpustStream(conn.getInputStream());

                        // android.view.ViewRootImpl$CalledFromWrongThreadException:
                        // Only the original thread that created a view hierarchy can touch its views.

                        //不能再子线程里去修改view。所有更新ui的操作，都必须在主线程内操作。

                        //content.setText(stringFromInpustStream); //在错误的线程里被调用

                        //通过消息机制，把数据发送给主线程，然后让主线程去修改UI

                        //发送消息给主线程
                        Message msg = new Message();

                        Log.i(TAG, "msg=" + msg);
                        msg.obj = stringFromInpustStream;

                        myHandler.sendMessage(msg);
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

    class MyThread extends Thread {

        @Override
        public void run() {

            String path = "http://10.0.2.2/MyAppServerProject/MyHtml.html";
            //failed to connect to localhost/127.0.0.1 (port 80) after 5000ms:
            //localhost 127.0.0.1本机地址
            //改成本机的ip地址 ：
            //如果是使用模拟器去测试自己电脑上的server，可以写10.0.2.2，模拟器自动会去连接当前电脑的指定端口

            //如果是真机开发
            //真机如果是使用的数据网络，不能跟内网的server通信
            //让自己的手机也在这个网段  192.168.3.33

            try {
                URL url = new URL(path);

                //URLConnection urlConnection = url.openConnection();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                //发请求之前，要把请求的一些参数配置好
                connection.setRequestMethod("GET");
                //连接超时时间
                connection.setConnectTimeout(5000);
                //读取数据的超时时间
                connection.setReadTimeout(1000);

                //举例，可以通过如下api去设置请求头
                connection.setRequestProperty("Accept-Charset", "utf-8");

                //去连接 同步的api
                connection.connect();  //发送完请求会阻塞，知道结果回来。

                //响应码从响应报文）
                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {

                    Log.i(TAG, "response ok!");
                    //解析响应的报文

                    //拿到一个输入流。就是负责把响应报文里的内容给到你
                    InputStream inputStream = connection.getInputStream();

                    FileOutputStream fos = new FileOutputStream(getFilesDir() + "/MyHtml.html");

                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                        fos.write(buffer, 0, len);
                    }

                    inputStream.close();
                    fos.close();
                } else {
                    Log.i(TAG, "response not ok! =" + responseCode);
                }

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

