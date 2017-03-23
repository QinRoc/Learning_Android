package com.example.uicomponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cskaoyan.day2.uicomponent.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main_layout);

        //找到页面的布局文件中的那个控件对应的类。（并不是新建）
        //View 用于显示的控件
        //TextView 是view的子类
        //向下转型
        //TextView tv = (TextView) findViewById(R.id.tv);
        TextView tv = (TextView) findViewById(R.id.textView);
        //找到 更改里面的文字
        tv.setText("你好，1234");

        //找到button
        Button button = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);

        button.setText("登录");
        //Button的点击事件处理

        //方法2
        //注册一个点击事件的监听器
        //当点击事件发生之后，系统会调用到注册接口的onclick方法
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("button is clicked again!");

                //Context
                //也是代码运行的上下文环境。（一般指的是在那个页面上）
              *//*  Toast t = Toast.makeText(MainActivity.this,"button clicked!",Toast.LENGTH_SHORT);
                //显示吐司
                t.show();*//*

                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }
        });*/

        //方法3
       /* MyOnClickListener l= new MyOnClickListener();
        button.setOnClickListener(l);*/

        //方法4
        button.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id=view.getId();
        if (id==R.id.button1) {
            Toast.makeText(MainActivity.this, "hello button", Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.button2){

            Toast.makeText(MainActivity.this, "hello button2", Toast.LENGTH_SHORT).show();

        }

    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this,"hello 1234",Toast.LENGTH_SHORT).show();
        }
    }

    //方法1
    //参数View v的意义。
    //它会将当前点击的控件的对象实例，做为参数传进来

    //把当前的点击的控件做为参数传进来的更重要的意义
     public void function1(View v){

         int id= v.getId();

         if (id==R.id.button1){

             Button button = (Button) v;
             button.setText("注销");

             //System.out.println("button is clicked!");
             Toast.makeText(MainActivity.this, "button1 is clicked!", Toast.LENGTH_SHORT).show();
         }else if (id==R.id.button2){

             Toast.makeText(MainActivity.this, "button2 is clicked!", Toast.LENGTH_SHORT).show();


         }

    }
}
