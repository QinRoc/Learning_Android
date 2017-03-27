package com.cskaoyan.week3.getpicdemoapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView imageView;
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    Bitmap obj = (Bitmap) msg.obj;
                    imageView.setImageBitmap(obj);
                    break;

                case 0:
                    imageView.setImageResource(R.drawable.error);
                    break;
            }
            //imageView.setImageResource();
            //imageView.setImageURI();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void getPic(View v) {

        //有缓存的版本
        File file = new File(getCacheDir() + "/mm.jpg");

        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(getCacheDir() + "/mm.jpg");
            imageView.setImageBitmap(bitmap);
            Log.i(TAG, "get pic from cache!");
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String path = "http://10.0.2.2/MyAppServerProject/mm.jpg";

                    try {
                        URL url = new URL(path);

                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        connection.setRequestMethod("GET");
                        connection.setReadTimeout(5000);
                        connection.setConnectTimeout(1000);

                        connection.connect();

                        int responseCode = connection.getResponseCode();
                        if (responseCode == 200) {

                            InputStream inputStream = connection.getInputStream();

                            //Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);

                            FileOutputStream fos = new FileOutputStream(getCacheDir() + "/mm.jpg");

                            byte[] bytes = new byte[1024];
                            int len = -1;
                            while ((len = inputStream.read(bytes, 0, 1024)) != -1) {
                                fos.write(bytes, 0, len);
                            }

                            inputStream.close();
                            fos.close();

                            Bitmap bitmap = BitmapFactory.decodeFile(getCacheDir() + "/mm.jpg");

                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = bitmap;
                            myHandler.sendMessage(msg);
                        } else {
                            Log.i(TAG, "request fial!");

                            Message msg = new Message();
                            msg.what = 0;
                            myHandler.sendMessage(msg);
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
