package com.example.homework_day_15_network_lib;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class Homework2_inquireID extends AppCompatActivity {

    private EditText idET;
    private String idNum;
    private TextView resultTV;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    resultTV.setText(msg.obj.toString());
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2_inquire_id);

        Log.i("onCreate", "onCreate");
        idET = (EditText) findViewById(R.id.id_number);
        resultTV = (TextView) findViewById(R.id.result);

    }

    public void inquireID(View view) {
        idNum = idET.getText().toString();
        Log.i("inquireID", idNum);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.k780.com:88/?app=idcard.get&idcard=" +
                idNum +
                "&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("onResponse", "response:" + response);

                        //JSONObject jsonObject = new JSONObject().getJSONObject(response);
                        Gson gson = new Gson();
                        IDinfoBean iDinfoBean = gson.fromJson(response, IDinfoBean.class);
                        Log.i("onResponse", "iDinfoBean:" + iDinfoBean);

                        Message message = new Message();
                        message.what = 1;
                        message.obj = iDinfoBean;
                        Log.i("onResponse", "message:" + message);
                        handler.sendMessage(message);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("onErrorResponse", "error");
            }
        });
        queue.add(stringRequest);
    }
}
