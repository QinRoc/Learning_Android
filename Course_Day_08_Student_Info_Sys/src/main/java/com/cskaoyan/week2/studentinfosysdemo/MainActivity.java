package com.cskaoyan.week2.studentinfosysdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et_age;
    private EditText et_gender;
    private EditText et_name;
    private LinearLayout ll_content;

    private ArrayList<Student> studentlist;
    private SQLiteDatabase sqLiteDatabase;

    //可以通过代码去写页面的一些元素。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*TextView tv = new TextView(this);
        tv.setText("hello world");
        setContentView(tv);*/

        setContentView(R.layout.main);

        et_age = (EditText) findViewById(R.id.et_age);
        et_gender = (EditText) findViewById(R.id.et_gender);
        et_name = (EditText) findViewById(R.id.et_name);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);

        //
        studentlist= new ArrayList<Student>();
        sqLiteDatabase = openDB();
    }

    private SQLiteDatabase openDB() {

            String path="/data/data/"+getPackageName()+"/mystudent.db";

            sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path, null);

            //新建表
            String createTableSql = "create table if not exists student ( " +
                    "name varchar(20),age char(2), gender char(1));";

            sqLiteDatabase.execSQL(createTableSql);

            return sqLiteDatabase;
    }

    public void addStudent(View v){

        String age = et_age.getText().toString();
        String gender = et_gender.getText().toString();
        String name = et_name.getText().toString();

        Student student = new Student(age,gender,name);
        studentlist.add(student);

        //动态的往一个viewGroup里增加一个TextView
        //怎么去动态创建一个TextView
        TextView  textView =  new TextView(this);
        textView.setText(  "姓名 = " +name + "年龄 = " +age +" 性别 = " + gender);
        textView.setTextColor(Color.CYAN);
        textView.setTextSize(20); //只能通过像素去设置
        textView.setBackgroundColor(Color.LTGRAY);

        ll_content.addView(textView);
    }

    public void saveAllStudent(View v){

        //插入操作
        ContentValues content = new ContentValues();

        if (sqLiteDatabase!=null)
            for (Student stu: studentlist) {

                content.put("name",stu.getName());
                content.put("gender",stu.getGender());
                content.put("age",stu.getAge());
                sqLiteDatabase.insert("student",null,content );
                content.clear();
            }
        }

    public void restoreInfo(View v){

        //1 把数据库里的数据 取出来放到studentlist里
        if (sqLiteDatabase!=null){
            Cursor cursor = sqLiteDatabase.rawQuery("select * from student", null);
            while (cursor.moveToNext()){
                String name = cursor.getString(0);
                String age = cursor.getString(1);
                String gender = cursor.getString(2);
                studentlist.add(new Student(age,gender,name));
            }
        }

        //2 把studentlist里的每一个student放在一个textview，然后再放入ll_content

        for (Student stu: studentlist) {

            TextView  textView =  new TextView(this);

            textView.setText(  "姓名 = " +stu.getName() + "年龄 = " +stu.getAge() +" 性别 = " + stu.getGender());
            textView.setTextColor(Color.CYAN);
            textView.setTextSize(20); //只能通过像素去设置
            textView.setBackgroundColor(Color.LTGRAY);

            ll_content.addView(textView);
        }
    }
}

class Student{

    String age       ;
    String gender    ;
    String name      ;

    public Student(String age, String gender, String name) {
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
