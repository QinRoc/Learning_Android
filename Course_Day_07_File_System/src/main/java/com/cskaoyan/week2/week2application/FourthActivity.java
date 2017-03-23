package com.cskaoyan.week2.week2application;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


//SharePreferences

public class FourthActivity extends AppCompatActivity {

    private CheckBox mycheckbox3;
    private CheckBox mycheckbox4;
    private CheckBox mycheckbox5;

    //WIFI时自动下载图片
    private boolean isAutoShowPicViaWIFI;

    //自动检测有没有新版本
    private boolean isAutoUpdate;

    //使用移动网络时关闭下载图片
    private boolean isNotShowPicViaMobile;
    private EditText editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        mycheckbox3 = (CheckBox) findViewById(R.id.checkBox3);
        mycheckbox4 = (CheckBox) findViewById(R.id.checkBox4);
        mycheckbox5 = (CheckBox) findViewById(R.id.checkBox5);

        editText6 = (EditText) findViewById(R.id.editText6);

        //从sp里取数据
        SharedPreferences sp=  getSharedPreferences("setting", Context.MODE_PRIVATE);

        isAutoShowPicViaWIFI = sp.getBoolean("isAutoShowPicViaWIFI", true);
        isAutoUpdate = sp.getBoolean("isAutoUpdate", true);
        isNotShowPicViaMobile = sp.getBoolean("isNotShowPicViaMobile", true);

        mycheckbox3.setChecked(isAutoShowPicViaWIFI);
        mycheckbox4.setChecked(isAutoUpdate);
        mycheckbox5.setChecked(isNotShowPicViaMobile);

        String nickname = sp.getString("nickname", "null");
        editText6.setText(nickname);
    }

    public void saveSetting(View v){

        if(mycheckbox3.isChecked()){
            isAutoShowPicViaWIFI=true;
        }else{
            isAutoShowPicViaWIFI=false;
        }

        if(mycheckbox4.isChecked()){
            isAutoUpdate=true;
        }else{
            isAutoUpdate=false;
        }
        
        if(mycheckbox5.isChecked()){
            isNotShowPicViaMobile=true;
        }else{
            isNotShowPicViaMobile=false;
        }

        String nickname = editText6.getText().toString();

        //第一步获取一个sp，getSharedPreferences
        SharedPreferences sp=  getSharedPreferences("setting", Context.MODE_PRIVATE);

        //第二步，把数据放到sp里
        SharedPreferences.Editor edit = sp.edit();
        
        edit.putBoolean("isAutoShowPicViaWIFI",isAutoShowPicViaWIFI);
        edit.putBoolean("isAutoUpdate",isAutoUpdate);
        edit.putBoolean("isNotShowPicViaMobile",isNotShowPicViaMobile);
        edit.putString("nickname",nickname);

        //第三步，保存数据
        edit.commit();

        Toast.makeText(this, "setting is saved!", Toast.LENGTH_SHORT).show();
    }
}
