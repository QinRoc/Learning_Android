package com.cskaoyan.week3.networklibdemoapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RequestQueue requestQueue;
    private SmartImageView smart_imageview;

    //第一步得到一个请求队列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //"http://10.0.2.2/MyAppServerProject/mm.jpg";
        smart_imageview = (SmartImageView) findViewById(R.id.smart_imageview);
        //smart_imageview.setImageUrl("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1052990977,3211251248&fm=58");

        requestQueue = Volley.newRequestQueue(this);
    }


    public void get(View v) {

        //第一步得到一个请求队列
        //RequestQueue requestQueue = Volley.newRequestQueue(this);

        //第二步构建一个往服务器的请求GET
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://10.0.2.2/MyAppServerProject/servlet/LoginServlet?username=王道&password=123",
                new Response.Listener<String>() {
                    //当收到响应的时候执行：在主线程内回调
                    @Override
                    public void onResponse(String response) {
                        if ("success".equals(response)) {
                            Toast.makeText(MainActivity.this, "success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "fail!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    //当发生错误的时候执行：在主线程内回调
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        //第三步把请求放入请求队列
        requestQueue.add(stringRequest);
    }

    public void post(View v) {

        //第二步构建一个往服务器的请求POST

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://10.0.2.2/MyAppServerProject/servlet/LoginServlet",
                new Response.Listener<String>() {
                    //当收到响应的时候执行：在主线程内回调
                    @Override
                    public void onResponse(String response) {
                        if ("success".equals(response)) {
                            Toast.makeText(MainActivity.this, "success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "fail!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    //当发生错误的时候执行：在主线程内回调
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", "王道");
                map.put("password", "123");
                return map;
            }
        };

        //第三步把请求放入请求队列
        requestQueue.add(stringRequest);
    }

    public void getPic(View v) {

        String url = "http://10.0.2.2/MyAppServerProject/mm.jpg";

        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        smart_imageview.setImageBitmap(response);
                    }
                },
                100,
                200,
                null,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, error.toString());
                    }
                }
        );

        requestQueue.add(imageRequest);
    }

    public void getJson(View v) {

        String url = "http://10.0.2.2/ServerAPIProject/categories.json";

      /*  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String retcode = response.getString("retcode");

                            Log.i(TAG,"retcode="+retcode);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );*/

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    //当收到响应的时候执行：在主线程内回调
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Categories categories = gson.fromJson(response, Categories.class);
                        if (categories.retcode == 200) {
                            Toast.makeText(MainActivity.this, "success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "fail!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    //当发生错误的时候执行：在主线程内回调
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

        );

        requestQueue.add(stringRequest);
    }


    public void myAsynckTask(View v) {

      /*  new MyAsyncTask(){
            //主线程执行
            @Override
            public void preExecute() {
                //设置一张网络图片下载之前显示的图片
                smart_imageview.setImageResource(R.drawable.c7);
            }

            //启动线程去做
            @Override
            public Bitmap executeInBackground() {

                String path ="http://img4.imgtn.bdimg.com/it/u=196405673,1932175745&fm=23&gp=0.jpg";

                try {
                    URL url= new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.connect();

                    int responseCode = connection.getResponseCode();

                    if (responseCode==200){

                        InputStream inputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        return  bitmap;
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return  null;

            }

            //主线程执行
            @Override
            public void afterExecute(Bitmap bitmap) {
                smart_imageview.setImageBitmap(bitmap);
            }
        }.execute();*/

        String path = "http://img4.imgtn.bdimg.com/it/u=196405673,1932175745&fm=23&gp=0.jpg";
        new GoogleAsycTask().execute(path);
    }

    //
    class GoogleAsycTask extends AsyncTask<String, Float, Bitmap> {

        @Override
        protected void onPreExecute() {
            smart_imageview.setImageResource(R.drawable.c7);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String path = params[0];

            try {
                URL url = new URL(path);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {

                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    return bitmap;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            smart_imageview.setImageBitmap(bitmap);
        }
    }
}
