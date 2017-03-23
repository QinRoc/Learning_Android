package com.cskaoyan.week2.pullparsedemo;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import com.cskaoyan.week2.pullparsedemo.bean.Student;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml不建议在这里做
        File file = new File(getFilesDir(), "student.xml");

        if (!file.exists()){
            copyFileToDataFolder_byAssets();
            //copyFileToDataFolder_byRaw();
        }
    }

    private void copyFileToDataFolder_byRaw() {
        Resources resources = getResources();
        InputStream inputStream = resources.openRawResource(R.raw.student);
    }

    private void copyFileToDataFolder_byAssets() {

        //资产管家
        AssetManager assets = getAssets();

        try {
            InputStream is = assets.open("student.xml");

            File filesDir = getFilesDir();

            FileOutputStream fis = new FileOutputStream(new File(filesDir,"student.xml"));

            byte[] buffer = new byte[1024];
            int len=0;
            while ((len=is.read(buffer,0,1024))!=-1){
                fis.write(buffer,0,len);
            }

            is.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(View v){

        //xml pull parser解析的步骤
        //  1.  获得解析器
        XmlPullParser xmlPullParser = Xml.newPullParser();

        ArrayList<Student> students = new ArrayList<Student>();

        try {
            //  2  parser.setInput(in, "UTF-8") 设置输入流以及编码
           // String path= Environment.getExternalStorageDirectory().getAbsolutePath() +"/student.xml";

            File file = new File(getFilesDir(), "student.xml");
            FileInputStream fis = new FileInputStream(file);
            xmlPullParser.setInput(fis,"utf-8");

            int next = xmlPullParser.next();
            Student stu=null;

            while (next!=XmlPullParser.END_DOCUMENT){

                switch (next){

                    /*case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG,"START_DOCUMENT");
                        break;

                    case XmlPullParser.END_DOCUMENT:
                        Log.i(TAG,"END_DOCUMENT");
                        break;*/

                    case XmlPullParser.START_TAG:
                        Log.i(TAG,"START_TAG");

                        if ( "student".equals(xmlPullParser.getName())){

                             stu = new Student();
                        }else if ("name".equals(xmlPullParser.getName())){

                            String name = xmlPullParser.nextText();
                            stu.setName(name);
                        }else if ("id".equals(xmlPullParser.getName())){

                            String id = xmlPullParser.nextText();
                            stu.setId( id );
                        }else if ("gender".equals(xmlPullParser.getName())){

                            String gender = xmlPullParser.nextText();
                            stu.setGender(gender);
                        }else if ("class".equals(xmlPullParser.getName())){

                            String sclass = xmlPullParser.nextText();
                            stu.setSclass(sclass);
                        }

                        break;

                    case  XmlPullParser.END_TAG:
                        Log.i(TAG,"END_TAG");
                        if ( "student".equals(xmlPullParser.getName())){//<studnet>标签的终止
                             students.add(stu);
                             stu=null;
                        }

                        break;

                    case XmlPullParser.TEXT:
                        Log.i(TAG,"TEXT");

                        break;
                }

                next = xmlPullParser.next();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG,students.toString());
    }

    public void saveToXml(View v){

        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("cs001","zhangsan","male","wd01"));
        students.add(new Student("cs002","lisi","male","wd01"));
        students.add(new Student("cs003","wangwu","male","wd01"));
        students.add(new Student("cs004","zhaoliu","male","wd01"));

        //拿到xml序列化器
        XmlSerializer xs = Xml.newSerializer();

        //定位需要生成的xml文件
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/students1.xml");

        try {
            FileOutputStream fos = new FileOutputStream(file);

            //做初始化
            xs.setOutput(fos, "utf-8");

            //具体的往xml文件开始写数据

            //写xml头和根节点
            xs.startDocument("utf-8", true);

            xs.startTag(null, "students");
             //写内部子节点
            for ( Student stu :students ) {

                xs.startTag(null, "student");  //<student>

                    xs.startTag(null, "name");   //<name>
                    //xs.attribute("","");
                    xs.text(stu.getName());      //zhangsan
                    xs.endTag(null, "name");     //</name>

                    //gender
                    xs.startTag(null,"gender");
                    xs.text(stu.getGender());
                    xs.endTag(null,"gender");

                    //id
                    xs.startTag(null,"id");
                    xs.text(stu.getId());
                    xs.endTag(null,"id");

                    //class
                    xs.startTag(null,"class");
                    xs.text(stu.getSclass());
                    xs.endTag(null,"class");

                xs.endTag(null, "student");//</student>
            }
            //根节点结束
            xs.endTag(null, "students");
            //关闭文档
            xs.endDocument();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
