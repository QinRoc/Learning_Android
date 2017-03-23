package com.roc.homework_day_06_broadcast_send;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Send","oncreate run i");
    }

    public void sendBroadcastToWorld(View view){
        Intent intent = new Intent();
        intent.setAction("com.roc.proletariat");
        intent.putExtra("Declaration","Workers of the world, unite!");
        sendBroadcast(intent);
        Toast.makeText(this, "Have Declared", Toast.LENGTH_SHORT).show();
    }

    public void sendOrderedBroadcastToRank(View view){
        Intent intent = new Intent();
        intent.setAction("com.roc.comrade");
        intent.putExtra("Call","Liberate the World!");
        sendOrderedBroadcast(intent,null);
        Toast.makeText(this, "Have called", Toast.LENGTH_SHORT).show();
    }
}
