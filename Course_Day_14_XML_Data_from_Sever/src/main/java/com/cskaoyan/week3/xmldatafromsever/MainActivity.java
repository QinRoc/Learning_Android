package com.cskaoyan.week3.xmldatafromsever;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MSG_OK = 1;
    public static final int MSG_OK_LOCATION = 2;
    public static final int MSG_ERROR = 0;
    private static final int MSG_URL_ERROR = -1;

    private TextView textview;
    Handler myhandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_OK:
                    ArrayList<Book> booklist = (ArrayList<Book>) msg.obj;
                    textview.setText(booklist.toString());
                    break;
                case MSG_OK_LOCATION:
                    LocationInfo info = (LocationInfo) msg.obj;
                    textview.setText(info.toString());
                    break;
                case MSG_ERROR:
                    Toast.makeText(MainActivity.this, "服务器返回错误", Toast.LENGTH_SHORT).show();
                    break;
                case MSG_URL_ERROR:
                    Toast.makeText(MainActivity.this, "网址有错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);
    }

    public void getData(View v) {

        new Thread(new Runnable() {

            @Override
            public void run() {

                // String path ="http://10.0.2.2/MyAppServerProject/book.xml";
                String path = "http://api.k780.com:88/?app=phone.get&phone=13800138000&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml";
                try {
                    URL url = new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {

                        InputStream inputStream = connection.getInputStream();
                        //
                        LocationInfo info = parseXmlData(inputStream);

                        Message msg = new Message();
                        msg.what = MSG_OK_LOCATION;
                        msg.obj = info;
                        myhandler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = MSG_ERROR;
                        myhandler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                    Message msg = new Message();
                    msg.what = MSG_URL_ERROR;
                    myhandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();//
                }
            }

            private LocationInfo parseXmlData(InputStream inputStream) {

                LocationInfo info = null;

                XmlPullParser parser = Xml.newPullParser();

                try {
                    parser.setInput(inputStream, "utf-8");
                    int next = parser.next();

                    while (next != XmlPullParser.END_DOCUMENT) {

                        if (next == XmlPullParser.START_TAG) {

                            String name = parser.getName();
                            switch (name) {
                                case "success":
                                    String s1 = parser.nextText();

                                    if (s1.equals("1")) {
                                        info = new LocationInfo();
                                    } else {
                                        return null;
                                    }
                                    break;
                                case "phone":
                                    info.setPohne(parser.nextText());
                                    break;
                                case "area":
                                    info.setArea(parser.nextText());
                                    break;
                                case "postno":
                                    info.setPostno(parser.nextText());
                                    break;
                                case "att":
                                    info.setAtt(parser.nextText());
                                    break;
                                case "ctype":
                                    info.setCtype(parser.nextText());
                                    break;
                                case "operators":
                                    info.setOperators(parser.nextText());
                                    break;
                                case "style_simcall":
                                    info.setStyle_simcall(parser.nextText());
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
                return info;
            }

            private List<Book> parseXmlData2(InputStream inputStream) {

                List<Book> booklist = new ArrayList<Book>();

                XmlPullParser parser = Xml.newPullParser();

                try {
                    parser.setInput(inputStream, "utf-8");
                    int next = parser.next();

                    Book book = null;
                    while (next != XmlPullParser.END_DOCUMENT) {

                        if (next == XmlPullParser.START_TAG) {

                            String name = parser.getName();
                            switch (name) {
                                case "书":
                                    book = new Book();
                                    break;
                                case "书名":
                                    book.setBookname(parser.nextText());
                                    break;
                                case "作者":
                                    book.setAuthor(parser.nextText());
                                    break;
                                case "售价":
                                    String s = parser.nextText();
                                    int i = Integer.parseInt(s);
                                    book.setPrice(i);
                                    break;
                                case "姓名":
                                    book.setPersoninfo(parser.nextText());
                                    break;
                            }
                        } else if (next == XmlPullParser.END_TAG) {

                            if ("书".equals(parser.getName())) {
                                booklist.add(book);
                                book = null;
                            }
                        }
                        next = parser.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return booklist;
            }
        }).start();
    }
}
