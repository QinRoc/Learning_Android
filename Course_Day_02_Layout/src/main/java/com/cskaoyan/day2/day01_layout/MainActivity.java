package com.cskaoyan.day2.day01_layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate : 当前的页面创建的时候执行
     * 目前可以认为是我们的app里面所有代码的入口函数
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面的布局文件
        setContentView(R.layout.frame_layout);
    }
}
