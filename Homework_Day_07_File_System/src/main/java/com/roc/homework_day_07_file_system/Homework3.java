package com.roc.homework_day_07_file_system;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Homework3 extends AppCompatActivity {

    private File externamStorageDirectory;
    //private List<String> jpgName;
    private ArrayList<String> jpgName;
    private String externalStorageState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3);

        externamStorageDirectory = Environment.getExternalStorageDirectory();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "需要读写SD卡以拷贝其上的文件", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2);
            }
        }


    }

    public void toSearchJPG(View view){
        jpgName = new ArrayList<String>();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "需要读取SD卡以搜索其上的jpg文件", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }else{
            Toast.makeText(this, "准备搜索jgp", Toast.LENGTH_SHORT).show();
            searchJPG(externamStorageDirectory);
            //File target = new File(externamStorageDirectory,"/DCIM/100ANDRO");
            //searchJPG(target);
            Log.i("JPG Names", jpgName.toString());
            Toast.makeText(this, jpgName.toString(), Toast.LENGTH_SHORT).show();
        }
     }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                searchJPG(externamStorageDirectory);
                //File target = new File(externamStorageDirectory,"/DCIM/100ANDRO");
                //searchJPG(target);//ok
                Log.i("JPG Names",jpgName.toString());
                Toast.makeText(this, jpgName.toString(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "请授予读取SD卡的权限", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode==2){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }else{
                Toast.makeText(this, "请授予读写SD卡的权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void searchJPG(File directory){

        externalStorageState = Environment.getExternalStorageState();
        if (!externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "未装载SD卡，无法完成操作", Toast.LENGTH_SHORT).show();
            return;
        }

        recurisionSearchJPG(directory);
    }

    public void recurisionSearchJPG(File directory){
        File[] files = directory.listFiles();
        for (File file:
                files) {
            if(file.isDirectory()){
                //searchJPG(file);
                recurisionSearchJPG(file);
            //}else if(file.getName().endsWith(".jpg")){
            }else if(file.isFile()){
                Log.i("file",file.getName());
                //if(file.getName().endsWith(".jpg")){
                if(file.getName().toLowerCase().endsWith(".jpg")){
                    Log.i("jpg",file.getName());
                    jpgName.add(file.getName());
                    //return;
                }
            }
        }
    }

    public void copy1(View view){
        File fileFrom =new File(externamStorageDirectory+"/DCIM/1.jpg");
        File fileTo = new File(externamStorageDirectory,"1.jpg");

        //fileFrom.getTotalSpace();
        //fileFrom.getUsableSpace();
        //fileFrom.getFreeSpace();//Returns the number of unallocated bytes in the partition <a* href="#partName">named</a> by this abstract path name.
        if (noEnoughSpace(fileFrom)) return;

        try {
            FileInputStream fileInputStream = new FileInputStream(fileFrom);
            FileOutputStream fileOutputStream = new FileOutputStream(fileTo);

            int read = 0;
            while((read = fileInputStream.read())!=-1){
                fileOutputStream.write(read);
            }

            fileInputStream.close();
            fileOutputStream.close();
            Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean noEnoughSpace(File fileFrom) {
        Long fileSize = fileFrom.length();
        StatFs statFs = new StatFs(externamStorageDirectory.getAbsolutePath());
        long availableBytes = statFs.getAvailableBytes();
        if(fileSize>availableBytes){
            Toast.makeText(this, "SD卡可用空间不足", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void copy2(View view){
        File fileFrom =new File(externamStorageDirectory+"/DCIM/100ANDRO/2.jpg");
        File fileTo = new File(externamStorageDirectory,"2.jpg");

        if (noEnoughSpace(fileFrom)) return;

        byte[] buffer = new byte[1024];

        try {
            FileInputStream fileInputStream = new FileInputStream(fileFrom);
            FileOutputStream fileOutputStream = new FileOutputStream(fileTo);

            int read = 0;
            while((read = fileInputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer);
            }

            fileInputStream.close();
            fileOutputStream.close();

            Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copy3(View view){
        File fileFrom =new File(externamStorageDirectory+"/Download/3.txt");
        File fileTo = new File(externamStorageDirectory,"3.txt");

        if (noEnoughSpace(fileFrom)) return;

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileFrom));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileTo));

            int read = 0;
            while((read=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(read);
            }

            bufferedInputStream.close();
            bufferedOutputStream.close();

            Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copy4(View view){
        File fileFrom =new File(externamStorageDirectory+"/Music/4.mp3");
        File fileTo = new File(externamStorageDirectory,"4.mp3");

        if (noEnoughSpace(fileFrom)) return;

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileFrom));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileTo));

            int read = 0;
            byte[] buffer = new byte[1024];
            while((read=bufferedInputStream.read(buffer))!=-1){
                bufferedOutputStream.write(buffer);
            }

            bufferedInputStream.close();
            bufferedOutputStream.close();

            Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
