package com.cskaoyan.week2.readcontactsdemoapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Contacts> contactsesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsesList = new ArrayList<Contacts>();
    }


    public void readContacts(View v) {

        ContentResolver contentResolver = getContentResolver();

        //第一步，查询第一个表raw_contacts
        Cursor cursor = contentResolver.query(
                Uri.parse("content://com.android.contacts/raw_contacts"),
                new String[]{"contact_id"},
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {

            //根据得到的每一个contact_id，去data表里查询每一个联系人的信息
            int contact_id = cursor.getInt(0);

            Contacts contact = new Contacts();

            Cursor cursor2 = contentResolver.query(
                    Uri.parse("content://com.android.contacts/data"),
                    new String[]{"mimetype", "data1"},
                    "raw_contact_id = ?",
                    new String[]{contact_id + ""},
                    null);

            while (cursor2.moveToNext()) {
                String type = cursor2.getString(0);
                String data = cursor2.getString(1);
                switch (type) {
                    case "vnd.android.cursor.item/email_v2":
                        contact.setEmail(data);
                        break;
                    case "vnd.android.cursor.item/phone_v2":
                        contact.setPhone(data);
                        break;
                    case "vnd.android.cursor.item/name":
                        contact.setName(data);
                        break;
                }
            }
            contactsesList.add(contact);
            cursor2.close();
        }
        cursor.close();

        Log.i("tag", contactsesList.toString());
    }
}
