package com.roc.homework_day_04_activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Homework4 extends AppCompatActivity {

    String phone;
    TextView toCall;
    String website;
    TextView toVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4);

        Log.d("Contact", "oncreate run d");

        toCall = (TextView) findViewById(R.id.toCall);
        if (toCall != null) {
            phone = toCall.getText().toString();
        }


        toVisit = (TextView) findViewById(R.id.toVisit);
        if (toVisit != null) {
            website = toVisit.getText().toString();
        }
    }

    /*
    *  <action android:name="android.intent.action.DIAL" />
       <category android:name="android.intent.category.DEFAULT" />
       <category android:name="android.intent.category.BROWSABLE" />
       <data android:mimeType="vnd.android.cursor.item/phone" />
    * */
    public void jumpToDialer(View view) {
        //Intent intent = new Intent();
        //intent.setAction("android.intent.action.DIAL");
        //intent.setAction("android.intent.action.CALL");
        //intent.addCategory("android.intent.category.BROWSABLE");
        //intent.setType("vnd.android.cursor.item/phone");
        //intent.setData(Uri.parse("tel:"+phone));
        //android.intent.action.CALL_BUTTON is better

        //直接拨出
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this,"请授予通话权限",Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    public void jumpToBrowser(View view){

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("http://"+website));
        startActivity(intent);
    }
}
