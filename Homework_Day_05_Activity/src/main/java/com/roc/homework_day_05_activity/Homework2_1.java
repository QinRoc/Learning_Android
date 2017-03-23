package com.roc.homework_day_05_activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Homework2_1 extends AppCompatActivity {

    private String TAG = "IntentToGallery";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2_1);

    }


    public void jumpToGallery(View v){

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
