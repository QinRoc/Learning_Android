package com.cskaoyan.week2.week2application;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThirdActivity extends AppCompatActivity {

    private EditText editText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editText5 = (EditText) findViewById(R.id.editText5);

        restoreDataFromFile();
    }

    private void restoreDataFromFile() {

        byte[] buffer = new byte[1024];
        int read = 0;

        try {
            FileInputStream fileInputStream = openFileInput("data.txt");
            read = fileInputStream.read(buffer);
            String string = new String(buffer,0,read);
            editText5.setText(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(View v){

        String string = editText5.getText().toString();

        //openFileOutput 在data/data/packagename/files/
        try {
            FileOutputStream fileOutputStream = openFileOutput("data.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();

            FileOutputStream fileOutputStream2 = openFileOutput("data2.txt",
                    Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
            fileOutputStream2.write(string.getBytes());
            fileOutputStream2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
