package com.example.homework_day_15_network_lib;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import static android.graphics.Bitmap.CompressFormat;

public class Homework4_Upload_Photo extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4__upload__photo);

        imageView = (ImageView) findViewById(R.id.imageView);

    }

    public void selectPic(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("image/jpeg");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Uri image = data.getData();
            //Log.i("onActivityResult","Uri:"+image);//Uri:content://media/external/images/media/20
            //Log.i("onActivityResult","data.getDataString():"+data.getDataString());//content://media/external/images/media/20
            imageView.setImageURI(image);
        }
    }

    public void upload(View view) {

        new Thread(new Runnable() {

            String BOUNDARY = UUID.randomUUID().toString(); // 边界标识
            String PREFIX = "--";
            String LINE_END = "\r\n";

            @Override
            public void run() {
                //Drawable drawable = imageView.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                //RequestQueue queue = Volley.newRequestQueue(this);

                //String path = "http://10.0.2.2/network/recieve";
                String path = "http://192.168.2.100:8080/java/controller";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    //connection.setRequestProperty("CONTENT_TYPE","multipart/form-data");//not work
                    //connection.setRequestProperty("Content-Type","multipart/form-data");//the request was rejected because no multipart boundary was found
                    connection.setRequestProperty("Content-Type", "multipart/form-data" + ";boundary=" + BOUNDARY);

                    //DataOutputStream dos = new DataOutputStream( conn.getOutputStream());
                    OutputStream outputStream = connection.getOutputStream();
                    StringBuffer sb = new StringBuffer();
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(LINE_END);

                    sb.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
                            + "uploadPicTest.jpg" + "\"" + LINE_END);
                    sb.append("Content-Type: application/octet-stream; charset=utf-8" + LINE_END);
                    sb.append(LINE_END);

                    outputStream.write(sb.toString().getBytes());

                    bitmap.compress(CompressFormat.JPEG, 62, outputStream);
                    //connection.setRequestProperty("CONTENT_TYPE","multipart/form-data");//Cannot set request property after connection is made

                    outputStream.write(LINE_END.getBytes());
                    byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                    outputStream.write(end_data);
                    outputStream.flush();
                    connection.connect();

                    Log.i("upload", "connection:" + connection);

                    int responseCode = connection.getResponseCode();
                    Log.i("upload", "responseCode:" + responseCode);
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();

                        //StringBuffer sb1 = new StringBuffer();
                        //int ss;
                        //while ((ss = inputStream.read()) != -1) {
                        //sb1.append((char) ss);
                        //}
                        //Log.e("response", "response:" + sb1.toString().);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                            baos.write(buffer, 0, len);
                        }

                        String response = baos.toByteArray().toString();
                        //Log.i("response","response:"+response);//[B@fb6b7cd
                        Log.i("response", "response:" + new String(baos.toByteArray(), "utf-8"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
