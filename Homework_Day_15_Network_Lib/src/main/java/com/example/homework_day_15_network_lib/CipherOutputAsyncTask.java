package com.example.homework_day_15_network_lib;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Roc on 2017/3/30.
 */

public abstract class CipherOutputAsyncTask extends AsyncTask {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    onPostExecute((String) msg.obj);
                    break;
            }
        }

        //onPostExecute();
    };

    public void output(String string) {

        final String input = string;

        onPreExecute(input);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String output = doInBackground(input);
                try {
                    //Thread.currentThread().wait(5000);
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = 1;
                message.obj = output;
                handler.sendMessage(message);
            }
        }).start();
    }

    //@Override
    protected abstract void onPreExecute(String input);

    //@Override
    protected abstract String doInBackground(String input);

    //@Override
    protected abstract void onPostExecute(String output);
}
