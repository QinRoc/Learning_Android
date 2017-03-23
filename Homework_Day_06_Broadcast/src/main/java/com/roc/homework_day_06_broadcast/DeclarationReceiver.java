package com.roc.homework_day_06_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Peng on 2017/3/18.
 */

public class DeclarationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String declaration = intent.getStringExtra("Declaration");
        Log.i("Declaration",declaration);
        Toast.makeText(context, declaration, Toast.LENGTH_LONG).show();

        /*intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("http://www.baidu.com"));
        context.startActivity(intent);*/

        /*intent.setAction("android.intent.action.PICK" );
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("image/jpeg");
        context.startActivity(intent);*/

        Intent intentActivity = new Intent();
        /*intentActivity.setAction("android.intent.action.PICK" );
        intentActivity.addCategory("android.intent.category.DEFAULT");
        intentActivity.setType("image/jpeg");*/
        intentActivity.setAction("android.intent.action.VIEW");
        intentActivity.addCategory("android.intent.category.DEFAULT");
        intentActivity.addCategory("android.intent.category.BROWSABLE");
        intentActivity.setData(Uri.parse("http://cpc.people.com.cn"));
        context.startActivity(intentActivity);
    }
}
