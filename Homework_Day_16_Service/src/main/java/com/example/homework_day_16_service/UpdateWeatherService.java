package com.example.homework_day_16_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateWeatherService extends Service {

    static String TAG = "UpdateWeatherService";

    public UpdateWeatherService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "onStartCommand");

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //String path = "http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
                String path = "http://api.k780.com:88/?app=weather.today&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";

                //ReferenceQueue queue = new ReferenceQueue();
                RequestQueue queue = Volley.newRequestQueue(UpdateWeatherService.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, path, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, "onResponse:" + response);

                        File weatherInfo = new File(getCacheDir() + "/weather.json");
                        try {
                            //Time time = new Time();
                            //time.getTime();
                            //Date date = new Date();
                            System.currentTimeMillis();
                            Date date = new Date(System.currentTimeMillis());

                            FileOutputStream fileOutputStream = new FileOutputStream(weatherInfo);
                            fileOutputStream.write((date + "\n" + response).getBytes());
                            fileOutputStream.close();
                            Log.i(TAG, "onResponse: Finished Storing in file");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse");
                    }
                });

                queue.add(stringRequest);
            }
        };

        long period = intent.getLongExtra("period", 30000);
        Log.i(TAG, "period:" + period);
        timer.schedule(timerTask, 1, period);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
