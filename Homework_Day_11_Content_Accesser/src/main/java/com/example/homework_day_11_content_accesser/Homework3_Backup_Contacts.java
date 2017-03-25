package com.example.homework_day_11_content_accesser;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Homework3_Backup_Contacts extends AppCompatActivity {

    private ContentResolver contentResolver;
    private ArrayList<ContactBean> contacts;
    private SQLiteDatabase contactsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__backup__contacts);

        contentResolver = getContentResolver();
        Contacts_DB_OpenHelper contactsDbOpenHelper = new Contacts_DB_OpenHelper(this, "contacts.db", null, 1);
        contactsDB = contactsDbOpenHelper.getWritableDatabase();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this, "需要读取联系人权限以保存联系人", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }
        }

        contentResolver.registerContentObserver(
                Uri.parse("content://com.android.contacts/raw_contacts"),
                false,
                new Contact_ContentObserver(null));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "请授予读取联系人的权限", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void backupContacts(View view) {
        ContentResolver contentResolver = getContentResolver();
        contacts = new ArrayList<>();

        Cursor query = contentResolver.query(Uri.parse("content://com.android.contacts/raw_contacts"),
                new String[]{"contact_id"},
                null,
                null,
                null);
        while (query.moveToNext()) {
            int contact_id = query.getInt(0);
            ContactBean contact = new ContactBean();
            Cursor query1 = contentResolver.query(Uri.parse("content://com.android.contacts/data"),
                    new String[]{"mimetype", "data1"},
                    "raw_contact_id = ?",
                    new String[]{contact_id + ""},
                    null);
            while (query1.moveToNext()) {
                String type = query1.getString(0);
                String data = query1.getString(1);
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
            contacts.add(contact);
            query1.close();
        }
        query.close();
        Log.i("getContacts", contacts.toString());

        for (ContactBean contact :
                contacts) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", contact.getName());
            contentValues.put("phone", contact.getPhone());
            contentValues.put("email", contact.getEmail());
            contactsDB.insert("contacts", null, contentValues);
        }
        Log.i("saveContacts", "保存完毕");
    }
}
