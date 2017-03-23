package com.cskaoyan.week2.week2application;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    private EditText editText4;
    private String stringFromEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText4 = (EditText) findViewById(R.id.editText4);
    }

    public void saveData(View v){

        stringFromEditText = editText4.getText().toString();

        //保存到sdcard的一个文件上
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        }else{
            saveToSdcard(stringFromEditText);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                saveToSdcard(stringFromEditText);
            }
        }
    }

    private void saveToSdcard(String string) {

        // 1 sd卡的路径建议使用官方的API去获取
        File externalStorageDirectory = Environment.getExternalStorageDirectory();

        File file = new File(externalStorageDirectory,"2.txt");

        //  2 要去获取SDcard的状态
        String externalStorageState = Environment.getExternalStorageState();
        if (!externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "sd不可用！", Toast.LENGTH_SHORT).show();
            return;
        }

        //判断SDcard是否已经满了
        //StatFs 获取某个文件夹的信息 一个工具类
        StatFs statFs = new StatFs(externalStorageDirectory.getAbsolutePath());

        //以下API是APIlevel 18以后才有的，
        //minSdkVersion 16 所以16 17这两个版本上也可以安装
        //根据不同的版本，去找该版本上对应的API来使用，避免报错

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1 ){

            Log.i(TAG,"sdcard="+externalStorageDirectory.getAbsolutePath());
            long availableBlocksLong = statFs.getAvailableBlocksLong(); //可用的block的个数
            long blockCountLong = statFs.getBlockCountLong(); // blocks 的总数 sd卡的
            long blockSizeLong = statFs.getBlockSizeLong(); //  每个block 块的size （字节）

            //可以计算sd卡出总的空间 以及 可用的空间
            long totalsize    = blockSizeLong*blockCountLong;  //字节
            long availableSize= blockSizeLong*availableBlocksLong;

            Log.i(TAG,"totalsize="+totalsize);
            Log.i(TAG,"availableSize="+availableSize);
        }else{

            int availableBlocksLong = statFs.getAvailableBlocks(); //可用的block的个数
            int blockCountLong = statFs.getBlockCount(); // blocks 的总数 sd卡的
            int blockSizeLong = statFs.getBlockSize(); //  每个block 块的size （字节）

            //可以计算sd卡出总的空间 以及 可用的空间
            long totalsize    = blockSizeLong*blockCountLong;  //字节
            long availableSize= blockSizeLong*availableBlocksLong;
            Log.i(TAG,"totalsize="+formatSize(totalsize));
            Log.i(TAG,"availableSize="+formatSize(availableSize));
        }

        Log.i(TAG,externalStorageState);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(string.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }
}
