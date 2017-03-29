package com.example.homework_day_14_xml_json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homework_day_14_xml_json.domain.NumberLocation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Homework2_Number_Location extends AppCompatActivity {

    private EditText phoneET;
    private String phone;
    private TextView textView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i("Handler", "Handler");

            switch (msg.what) {
                case 1:
                    NumberLocation location = (NumberLocation) msg.obj;
                    textView.setText(location.toString());
                    break;
                case 2:
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    textView.setText(jsonObject.toString());
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2__number__location);
        phoneET = (EditText) findViewById(R.id.phone);

        Log.i("onCreate", "onCreate");

        textView = (TextView) findViewById(R.id.result);
    }

    public void inquirePhoneXML(View view) {

        Log.i("inquirePhoneXML", "inquirePhoneXML");

        phone = phoneET.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i("run", "run");

                NumberLocation location = new NumberLocation();

                String path = "http://api.k780.com:88/?app=phone.get&phone=" +
                        phone +
                        "&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    Log.i("run", "responseCode:" + responseCode);
                    if (responseCode == 200) {

                        InputStream inputStream = connection.getInputStream();
                        /*
                        * <root>
                            <success>1</success>
                            <result>
                                    <phone>13800138000</phone>
                                    <area>010</area>
                                    <postno>100000</postno>
                                    <att>中国,北京</att>
                                    <ctype>北京移动全球通卡</ctype>
                                    <par>1380013</par>
                                    <prefix>138</prefix>
                                    <operators>移动</operators>
                                    <style_simcall>中国,北京</style_simcall>
                                    <style_citynm>中华人民共和国,北京市</style_citynm>
                            </result>
                          </root>
                        */
                        XmlPullParser parser = Xml.newPullParser();

                        parser.setInput(inputStream, "utf-8");
                        int next = parser.next();
                        Log.i("run", "next:" + next);
                        while (next != XmlPullParser.END_DOCUMENT) {
                            if (next == XmlPullParser.START_TAG) {
                                String name = parser.getName();
                                Log.i("run", "name:" + name);
                                switch (name) {
                                    case "success":
                                        String success = parser.nextText();
                                        Log.i("switch", "success:" + success);
                                        if ("1".equals(success)) {
                                            location = new NumberLocation();
                                        } else {
                                            return;
                                        }
                                        break;
                                    case "phone":
                                        location.setPhone(parser.nextText());
                                        break;
                                    case "area":
                                        location.setArea(parser.nextText());
                                        break;
                                    case "postno":
                                        location.setPostno(parser.nextText());
                                        break;
                                    case "att":
                                        location.setAtt(parser.nextText());
                                        break;
                                    case "ctype":
                                        location.setCtype(parser.nextText());
                                        break;
                                    case "par":
                                        location.setPar(parser.nextText());
                                        break;
                                    case "prefix":
                                        location.setPrefix(parser.nextText());
                                        break;
                                    case "operators":
                                        location.setOperators(parser.nextText());
                                        break;
                                    case "style_simcall":
                                        location.setStyle_simcall(parser.nextText());
                                        break;
                                    case "style_citynm":
                                        location.setStyle_citynm(parser.nextText());
                                        break;
                                }
                            }

                            next = parser.next();
                        }

                    } else {
                        Log.i("run", "无法连接");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                message.what = 1;
                message.obj = location;
                Log.i("run", "message:" + message);
                handler.sendMessage(message);
            }
        }).start();
    }

    public void inquirePhoneJSON(View view) {

        Log.i("inquirePhoneJSON", "inquirePhoneJSON");

        phone = phoneET.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i("run", "run");

                NumberLocation location = new NumberLocation();

                String path = "http://api.k780.com:88/?app=phone.get&phone=" +
                        phone +
                        "&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";

                URL url = null;
                try {
                    url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    Log.i("run", "responseCode:" + responseCode);
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                            byteArrayOutputStream.write(buffer, 0, len);
                        }

                        String result = new String(byteArrayOutputStream.toByteArray(), "utf-8");

                        byteArrayOutputStream.close();
                        inputStream.close();

                        JSONObject jsonObject = new JSONObject(result);

                        String success = jsonObject.getString("success");

                        if ("1".equals(success)) {

                            JSONObject resultJSON = jsonObject.getJSONObject("result");

                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = resultJSON;
                            handler.sendMessage(msg);
                        } /*else {
                            Message msg = new Message();
                            msg.what = 0;
                            handler.sendMessage(msg);
                        }*/
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
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
