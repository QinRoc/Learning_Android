package com.cskaoyan.week3.servicedemoapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhao on 2017/3/30.
 */

public class MyListenCallService extends Service {

    private static final String TAG = "MyListenCallService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart");

        //当服务启动的时候开始监听
        //调用系统的API进行电话状态的监听
        TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        //监听来电话状态
        //电话状态发生变化时候需要告诉我。
        MyPhoneStateListener myPhoneStateListener = new MyPhoneStateListener();
        mgr.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    class MyPhoneStateListener extends PhoneStateListener {

        MediaRecorder mRecorder = null;

        //当电话状态发生变化的时候，会去回调这个api
        //state 表示 当前电话的状态，
        //incomingNumber 表示来电号码
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //super.onCallStateChanged(state, incomingNumber);

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.i(TAG, "state=0 空闲状态");
                    //没电话之前。idle
                    //挂断电话之后 idle
                    //把之前的录音停掉，保存。
                    if (mRecorder != null) {
                        mRecorder.stop();
                        mRecorder.release();
                        mRecorder = null;
                        Log.i(TAG, "state=0 stop recording");
                    }
                    break;

                case TelephonyManager.CALL_STATE_RINGING:
                    //响铃状态，没接
                    Log.i(TAG, "state=1 响铃状态" + "incomingNumber" + incomingNumber);
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    //接通之后
                    Log.i(TAG, "state=2 通话状态");

                    //这里开始录音
                    //Initialize a new instance of MediaRecorder
                    mRecorder = new MediaRecorder();
                    //设置有音频的来源
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    //设置录音需要保存的格式3gpp
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    //设置录音需要保存的文件路径
                    File externalStorageDirectory = Environment.getExternalStorageDirectory();
                    mRecorder.setOutputFile(externalStorageDirectory.getAbsolutePath() + "/callrecord.3gp");
                    //设置音频的编码器
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                    try {
                        mRecorder.prepare();
                        mRecorder.start();
                    } catch (IOException e) {
                        Log.e(TAG, "prepare() failed");
                    }
                    Log.i(TAG, "state=2 start record！");

                    break;
            }
        }
    }
}
