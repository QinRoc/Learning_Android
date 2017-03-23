package com.cskaoyan.day2.day06broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MySMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        Bundle extras = intent.getExtras();
        Object[] objs = (Object[]) extras.get("pdus");//pdus是sms的制式
        for (Object object : objs) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) object);
            String body = smsMessage.getMessageBody(); //短信内容
            String address = smsMessage.getOriginatingAddress(); //发信人

            if("95555".equals(address)) {
                System.out.println("招行短信, 号码: " + address + ", 内容: " + body);
                //abortBroadcast();
            }
        }
    }
}
