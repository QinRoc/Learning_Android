package com.cskaoyan.week3.week3application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

//补充下，主线程内不能去执行耗时操作

//我们在android开发的时候，我们去实现的callback，哪些是在主线程上执行的呢？
//四大组件上的callback都是在主线程上执行的。
//mainactivity内的所有callback都是
//fragment内的所有callback都是
//BroadcastReceiver 的onreceive默认是在主线程上执行的。
//ContentProvider 内的增删改查方法也是在主线程上执行的。

//主线程在android内作用非常大。
//刷新UI,处理用户的响应
//在android系统上，主线程又名：UI线程

//如何避免ANR？
//尽可能避免在主线程执行耗时操作，如果有的话，去启动子线程去处理。

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //引发anr，没响应

    //主线程被阻塞，不能继续执行
    //产生的问题是：
    //点击button之后，button的ui是不是会发生变化？是，刷新UI
    //这些代码是在哪执行的？是在主线程内执行的。

    //一旦你在主线程内做一个非常耗时的操作，让主线程一直在这个操作上不能继续往下走
    //其影响就是主线程无法去刷新应有的UI变化。
    //本质上是无法去执行其他的任何代码，对于用户来说就是不能处理任何其他响应

    //如果在主线程里占用了太长的时间的话
    //在android中Activity的最长执行时间是5秒
    //系统为了解决这种长时间没响应的状态，会弹出这个ANR窗口去清用户决定要不要终止这个应用。
    //在操作系统级别上kill这个Process 进程。
    //此时会引发ANR

    public void click(View v) {

        //模拟这里面在处理数据，需要执行一段时间才结束

        //返回当前线程：主线程
        Thread thread = Thread.currentThread();

        /*try {
            thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Toast.makeText(this, "clicked!", Toast.LENGTH_SHORT).show();

        //去遍历SD卡上的所有文件
        //这个遍历操作是不是耗时操作？是
        //需要消耗多少时间？

        //ANR是一个逻辑问题，而非语法问题
        //没办法从语法或者给一个完美的机制让别人去避免。
        //需要程序员从逻辑方面去解决。

        //所以android 在2.3之后有一个强制规定。
        //凡是耗时的操作(文件的IO，网络操作等)，都不能在主线程内执行。要放到子线程里去执行。
        //以免引发ANR.
    }
}
