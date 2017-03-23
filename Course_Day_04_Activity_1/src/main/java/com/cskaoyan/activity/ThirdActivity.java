package com.cskaoyan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cskaoyan.bean.Student;
import com.cskaoyan.bean.User;
import com.cskaoyan.day2.day04activitydemo.R;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void jumpAndSend(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name","zhangsan");

        //serializable的数据
        User user= new User();
        user.setAge(22);
        user.setName("zhangsan");
        intent.putExtra("user",user);

        //parcelable的数据
        Student student = new Student();
        student.setAge(23);
        student.setName("lisi");
        intent.putExtra("stu",student);

        //只有启动的时候调用这个api，才可以接收到返回的数据
        startActivityForResult(intent,100);
    }

    //当startActivityForResult返回的时候 的一个callback函数，在该函数内接受返回的数据


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG,requestCode+"");
        Log.i(TAG,resultCode+"");

        if (requestCode==100){

            String result = data.getStringExtra("result");
            Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        }


    }
}
