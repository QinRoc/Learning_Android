package com.roc.homework_day_08_pull_parse_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Homework3_SQLite extends AppCompatActivity {

    private EditText nameET;
    private EditText ageET;
    private EditText genderET;
    private String name;
    private String age;
    private String gender;
    private SQLiteDatabase sqliteDatabase;
    private EditText idET;
    private String id;
    private LinearLayout ll_students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__sqlite);

        idET = (EditText) findViewById(R.id.et_id);
        nameET = (EditText) findViewById(R.id.et_name);
        ageET = (EditText) findViewById(R.id.et_age);
        genderET = (EditText) findViewById(R.id.et_gender);

        id = idET.getText().toString();
        name = nameET.getText().toString();
        age = ageET.getText().toString();
        gender = genderET.getText().toString();

        ll_students = (LinearLayout) findViewById(R.id.ll_content);

        openDB();
    }

    private SQLiteDatabase openDB(){
        sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir()+"/students.db",null);

        String createTableSql = "create table if not exists students ( " +
                //"id char(10) primary key auto_increment, name varchar(20),age char(2), gender char(1));";
                "id char(10) primary key, name varchar(20),age char(2), gender char(1));";

        sqliteDatabase.execSQL(createTableSql);

        return sqliteDatabase;
    }

    public void addStudent(View view){
        String addAStudent = "insert into students values(?,?,?,?)";
        sqliteDatabase.execSQL(addAStudent,new Object[]{id,name,age,gender});

        TextView textView =  new TextView(this);
        textView.setText(  "学号："+id+"姓名： " +name + "年龄： " +age +" 性别： " + gender);
        textView.setTextColor(Color.CYAN);
        textView.setTextSize(20); //只能通过像素去设置
        textView.setBackgroundColor(Color.LTGRAY);

        ll_students.addView(textView);

    }

    public void deleteStudent(View view){

    }

    public void alterStudent(View view){

    }

    public void searchStudent(View view){

    }

    public void saveAllStudent(View view){

    }

    public void restoreInfo(View view){

    }



}

class student{
    private String id;
    private String name;
    private String age;
    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public student() {
    }

    public student(String id, String name, String age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
