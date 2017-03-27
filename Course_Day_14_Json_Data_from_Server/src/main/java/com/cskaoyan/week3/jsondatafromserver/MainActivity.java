package com.cskaoyan.week3.jsondatafromserver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView textView;
    Handler myhandler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    JSONObject obj = (JSONObject) msg.obj;
                    try {
                        textView.setText(obj.getString("style_citynm"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    Toast.makeText(MainActivity.this, "error!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.content);
    }

    public void getJson(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String path = "http://api.k780.com:88/?app=phone.get&phone=13632839272&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";

                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(1000);
                    connection.setRequestMethod("GET");
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {

                        InputStream inputStream = connection.getInputStream();

                        String stringFromInputstream = HttpUtils.getStringFromInputstream(inputStream);

                        //parse(stringFromInputstream);

                        JSONObject jsonObject = new JSONObject(stringFromInputstream);

                        String success = jsonObject.getString("success");

                        if ("1".equals(success)) {

                            JSONObject result = jsonObject.getJSONObject("result");

                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = result;
                            myhandler.sendMessage(msg);
                        } else {
                            Message msg = new Message();
                            msg.what = 0;
                            myhandler.sendMessage(msg);
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            private void parse(String stringFromInputstream) throws JSONException {

                JSONObject jsonObject = new JSONObject(stringFromInputstream);

                String coutryname = jsonObject.getString("name");
                Log.i(TAG, "coutryname=" + coutryname);

                JSONArray province = jsonObject.getJSONArray("province");
                JSONObject jsonObject1 = province.getJSONObject(0);
                String name = jsonObject1.getString("name");
                Log.i(TAG, "provincename=" + name);

                JSONObject cities = jsonObject1.getJSONObject("cities");
                JSONArray city = (JSONArray) cities.get("city");
                Object o = city.get(0);
                Log.i(TAG, "city0=" + o);
            }
        }).start();
    }
}
