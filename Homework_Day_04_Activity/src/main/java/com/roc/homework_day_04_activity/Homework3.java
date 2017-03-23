package com.roc.homework_day_04_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.roc.homework_day_04_activity.com.roc.domain.RPBean;

import static com.roc.homework_day_04_activity.R.id.result;

public class Homework3 extends AppCompatActivity {

    EditText nameET;
    TextView resultTV;
    Intent intentTo;
    //int calResult;

    RPBean rpBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3);

        nameET = (EditText) findViewById(R.id.name);
        resultTV = (TextView) findViewById(result);
        //intentTo = new Intent();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("onActivity",requestCode+"");
        Log.i("onActivity",resultCode+"");
        if (requestCode==100){

            rpBean = (RPBean) data.getSerializableExtra("rpBean");
            //Intent intentFrom = getIntent();
            nameET.setText(rpBean.getName());
            resultTV.setText("您的人品分是："+rpBean.getRp()+"\n"+rpBean.getRpText());
        }
    }

    public void toCalculate(View view){
        intentTo = new Intent(this,Homework3_result.class);
        intentTo.putExtra("name",nameET.getText().toString());
        //startActivity(intentTo);
        startActivityForResult(intentTo,100);
    }

}
