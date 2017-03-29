package com.example.homework_day_15_network_lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Homework3_Asynchronous_Task extends AppCompatActivity {

    private EditText beCodeET;
    private TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__asynchronous__task);

        Log.i("onCreate", "onCreate");
        beCodeET = (EditText) findViewById(R.id.tobeCode);
        resultTV = (TextView) findViewById(R.id.result);
    }

    public void cipherOutput(View view) {
        String input = beCodeET.getText().toString();
        Log.i("cipherOutput", "input");
        new CipherOutput().output(input);
    }

    class CipherOutput extends CipherOutputAsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPreExecute(String input) {
            resultTV.setText("正在加密：" + input);
        }

        @Override
        protected String doInBackground(String input) {

            String output = "";

            //output = new String(input.getBytes("iso-8859-6"),"iso-8859-1");

            for (char word :
                    input.toCharArray()) {
                output += ++word;
            }

            return output;
        }

        @Override
        protected void onPostExecute(String output) {
            resultTV.setText("加密后的密码为：" + output);
        }
    }
}
