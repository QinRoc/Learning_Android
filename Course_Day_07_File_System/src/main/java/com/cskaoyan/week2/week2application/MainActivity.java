package com.cskaoyan.week2.week2application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    private EditText username_et;
    private EditText password_et;
    private Button login_bt;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_et = (EditText) findViewById(R.id.editText);
        password_et = (EditText) findViewById(R.id.editText3);
        login_bt = (Button) findViewById(R.id.button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        login_bt.setOnClickListener(this);

        //下一次重新进入该Activity的时候，去文件系统里把用户名或者密码读出来
        //放在指定的控件上即可。
        readFromFile3();
    }

    private void readFromFile3() {
       // File file = new File("/data/data/com.cskaoyan.week2.week2application/mydata3.txt");

       // File filesDir = getFilesDir();

        File filesDir = getCacheDir();
        Log.i(TAG,filesDir.getAbsolutePath());

        File file = new File(filesDir,"mydata3.txt");

        try {
            InputStreamReader isr= new InputStreamReader(new FileInputStream(file) ,"utf-8");

            char[] buffer =new char[1024];
            int read = isr.read(buffer);

            String string = new String(buffer,0,read);

            isr.close();

            username_et.setText(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字符输入流实现
    private void readFromFile2() {

        File file = new File("/data/data/com.cskaoyan.week2.week2application/mydata2.txt");

        try {
            InputStreamReader isr= new InputStreamReader(new FileInputStream(file) ,"utf-8");

            char[] buffer =new char[1024];
            int read = isr.read(buffer);

            String string = new String(buffer,0,read);

            isr.close();

            username_et.setText(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字节输入流
    private void readFromFile1() {
        //字节输入流
        byte[] buffer = new byte[1024];
        try {

            FileInputStream fileInputStream = new FileInputStream("/data/data/com.cskaoyan.week2.week2application/mydata.txt");

            int read = fileInputStream.read(buffer);

            String s = new String(buffer,0,read);

            username_et.setText(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        String username = username_et.getText().toString();
        String password = password_et.getText().toString();

        //判断记住用户名的checkbox是否勾选
        boolean checked = checkBox.isChecked();

        if ("王道".equals(username)&&"123".equals(password)){

            Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
            //
            if (checked){

                //Toast.makeText(this, "用户选择了记住用户名", Toast.LENGTH_SHORT).show();
                //可以把用户名或者密码保存在文件系统 username
                //输出流 字节 字符   先用字节流输出流实现

                saveToFile3(username);
                startActivity(new Intent(this,FourthActivity.class));
            }
        }else{
            Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
        }
    }

    //
    private void saveToFile3(String username) {

        //File file = new File("/data/data/com.cskaoyan.week2.week2application/mydata2.txt");

        //File filesDir = getFilesDir();

        File filesDir = getCacheDir();
        Log.i(TAG,filesDir.getAbsolutePath());

        File file = new File(filesDir,"mydata3.txt");
        try {
            //不写,系统默认的编码是utf-8
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));

            osw.write(username);

            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字符流实现
    private void saveToFile2(String username) {

        File file = new File("/data/data/com.cskaoyan.week2.week2application/mydata2.txt");

        try {
            //不写,系统默认的编码是utf-8
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));

            osw.write(username);

            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字节流实现
    private void saveToFile1(String username) {
    }
}
