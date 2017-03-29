package com.cskaoyan.week3.networklibdemoapp;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

/**
 * Created by zhao on 2017/3/29.
 */

//一个访问网络的异步执行框架
//需要先在主线程内执行一个初始化工作
//需要另起一个线程去执行任务
//当任务执行完毕之后，通知主线程去执行另一个函数

public abstract class MyAsyncTask {

    Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                Bitmap bitmap = (Bitmap) msg.obj;
                afterExecute(bitmap);
            }
        }
    };

    //得让我们三个函数按照预定的期望执行起来
    public void execute() {

        preExecute();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = executeInBackground();

                Message message = new Message();
                message.what = 1;
                message.obj = bitmap;
                myHandler.sendMessage(message);
            }
        }).start();
    }

    //该函数在线程启动之前在主线程执行
    public abstract void preExecute();

    //该函数内的代码是起线程，在线程内异步执行
    public abstract Bitmap executeInBackground();

    //去通知主线程，回调另一个函数
    public abstract void afterExecute(Bitmap bitmap);
}
