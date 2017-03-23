package com.roc.homework_day_05_activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Homework2_2 extends AppCompatActivity {

    String phone;
    TextView toCall;
    String website;
    TextView toVisit;

    private String TAG = "Permission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2_2);

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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //Toast.makeText(this,"请授予通话权限",Toast.LENGTH_SHORT).show();
            //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},100);
            //return;

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                //如果是的话，就额外多做点提示给用户解释为啥需要改权限。
                //自己写dialog。用户确认之后再去申请。
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(this,"请授予通话权限以便联系我们",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(Homework2_2.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        100);
                /*new AlertDialog.Builder(this)
                        .setTitle("解释")
                        .setMessage("很严肃的，很重要,所以要申请这个权限")
                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Homework2_2.this,
                                        new String[]{Manifest.permission.CALL_PHONE},
                                        100);

                                Log.i(TAG, "requestPermissions");
                            }
                        })

                        .show();*/
            } else {
                //如果是第一次 或者用户选择不必询问，都会走这里。
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},100);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            startActivity(intent);
        }
        //startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
            }else{
                Toast.makeText(this,"请授予通话权限",Toast.LENGTH_SHORT).show();
            }
        }
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
