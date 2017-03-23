package com.cskaoyan.day2.day04activitydemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.cskaoyan.activity.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转到第二个Activity
     * 新建一个意图, 指定跳转到哪一个activity类.
     * @param v
     */

    //跳转方式1
    public void jump(View v){

        //在意图里，指定跳转到哪一个activity类.
        //第一个参数是一个上下文，从哪里跳，
        // 第二个参数是一个字节码文件对象，表示跳到哪里
        Intent intent = new Intent(this, SecondActivity.class);
        //把意图传递给系统
        startActivity(intent);
    }

    //跳转方式2 通过指定要跳转的页面的全类名
    public void jump2(View v){

        //在意图里，指定跳转到哪一个activity类.

        Intent intent = new Intent();
        //intent.setClass(this, SecondActivity.class)
        intent.setClassName(this,"com.cskaoyan.activity.SecondActivity");
        //把意图传递给系统
        startActivity(intent);

        Log.i(TAG,"jump2");
    }

    //跳转方式3 通过指定要跳转的页面的全类名
    public void jump3(View v){

        //在意图里，指定跳转到哪一个activity类.

        Intent intent = new Intent();
        //intent.setClass(this, SecondActivity.class)
        intent.setClassName("com.cskaoyan.day2.day04activitydemo","com.cskaoyan.activity.SecondActivity");
        //把意图传递给系统
        startActivity(intent);

        Log.i(TAG,"jump3");
    }



    //方式四 隐式跳转
    //隐式跳转会默认加上default Category
    //我们要隐式跳到某个Activity，需要知道它的
     /* <intent-filter>
                <action   android:name="com.cskaoyan.comeandfindme"></action>
                <action   android:name="com.cskaoyan.home"></action>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="com.cskaoyan.zhao"/>
                <category android:name="com.cskaoyan.lan"/>
                 <data android:scheme="abc"/>
                <data android:host="www.cskaoyan.com"/>
                <data android:port="8080"/>
                <data android:path="java"/>
            </intent-filter>*/
     //category 和action 如果有多个，匹配到一个即可
    public void jump4(View v){

        Intent intent = new Intent();
        intent.setAction("com.cskaoyan.comeandfindher");
        intent.addCategory("com.cskaoyan.zhang");
        //intent.setType("big/small");
        //intent.setData(Uri.parse("abc://www.cskaoyan.com:8080/java"));
        //No Activity found to handle Intent
        // { act=com.cskaoyan.comeandfindher cat=[com.cskaoyan.zhang] typ=big/small }

        intent.setDataAndType(Uri.parse("abc://www.cskaoyan.com:8080/java"),"big/small");

        Log.v(TAG,"jump4");
        startActivity(intent);
    }


    public void jumpToApp(View v){

        //以下代码是跳到浏览器Activity的
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    public void jumpToApp2(View v){

        //以下代码是跳到图库Activity的
        /*<intent-filter>
                <action android:name="android.intent.action.PICK" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="image*//*" />
                <data android:mimeType="video*//*" />
            </intent-filter>*/
         Intent intent = new Intent();
         intent.setAction("android.intent.action.PICK" );
         intent.addCategory("android.intent.category.DEFAULT");
         intent.setType("image/jpeg");
         startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         Log.i(TAG,requestCode+"");
         if (requestCode==100){

             ImageView imageView = (ImageView) findViewById(R.id.imageView);

             //imageView.setImageResource(R.mipmap.ic_launcher);

             Uri data1 = data.getData();
             imageView.setImageURI(data1);
         }
    }
}
