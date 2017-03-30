package com.example.homework_day_16_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    private TextView weatherTV;
    private EditText periodET;
    //private ListenService.ListenBinder binder;
    //private ListenServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate");
        periodET = (EditText) findViewById(R.id.period);
        weatherTV = (TextView) findViewById(R.id.weather);
        showWeather();
    }

    public void showWeather() {
        Log.i(TAG, "showWeather");


        String response = "";

        File weatherInfo = new File(getCacheDir() + "/weather.json");
        try {
            FileInputStream fileInputStream = new FileInputStream(weatherInfo);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = fileInputStream.read(buffer, 0, 1024)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            response = byteArrayOutputStream.toString();
            Log.i(TAG, "showWeather:response:" + response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        weatherTV.setText(response);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        //binder.unregister();
        //unbindService(connection);
    }

    /*class ListenServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"onServiceConnected");

            binder = (ListenService.ListenBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"onServiceDisconnected");
        }
    }*/

    public void listen(View view) {
        Intent intent = new Intent(this, ListenService.class);
        //connection = new ListenServiceConnection();
        //bindService(intent, connection,BIND_AUTO_CREATE);
        startService(intent);
        Log.i(TAG, "服务注册完成");
        Toast.makeText(this, "服务注册完成", Toast.LENGTH_LONG).show();
    }

    public void updateWeather(View view) {

        int minutes = Integer.parseInt(periodET.getText().toString());
        Intent intent = new Intent(this, UpdateWeatherService.class);
        intent.putExtra("period", (long) minutes * 60 * 1000);
        startService(intent);
        Log.i(TAG, "天气更新服务注册完成");
        Toast.makeText(this, "天气更新服务注册完成", Toast.LENGTH_LONG).show();
    }
}
