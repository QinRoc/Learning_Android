package com.roc.homework_day_08_pull_parse_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__sqlite);

        Log.i("SQLite", "oncreate run i");

        idET = (EditText) findViewById(R.id.et_id);
        nameET = (EditText) findViewById(R.id.et_name);
        ageET = (EditText) findViewById(R.id.et_age);
        genderET = (EditText) findViewById(R.id.et_gender);

        ll_students = (LinearLayout) findViewById(R.id.ll_content);
        students = new ArrayList<>();

        openDB();
    }

    private SQLiteDatabase openDB(){

        Log.i("openDB", "to open DB");

        id = idET.getText().toString();
        name = nameET.getText().toString();
        age = ageET.getText().toString();
        gender = genderET.getText().toString();

        sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir()+"/students.db",null);

        String createTableSql = "create table if not exists students ( " +
                //"id char(10) primary key auto_increment, name varchar(20),age char(2), gender char(1));";
                "id char(10) primary key, name varchar(20),age char(2), gender char(1));";

        sqliteDatabase.execSQL(createTableSql);

        Log.i("openDB", "opened DB");

        return sqliteDatabase;
    }

    public void addStudent(View view){

        Log.i("addStudent", "to add student");

        id = idET.getText().toString();
        name = nameET.getText().toString();
        age = ageET.getText().toString();
        gender = genderET.getText().toString();

        String addAStudent = "insert into students values(?,?,?,?)";
        sqliteDatabase.execSQL(addAStudent,new Object[]{id,name,age,gender});

        Log.i("addStudent", "added student");

        TextView textView =  new TextView(this);
        textView.setText("学号：" + id + " 姓名：" + name + " 年龄：" + age + " 性别：" + gender);
        textView.setTextColor(Color.CYAN);
        textView.setTextSize(20); //只能通过像素去设置
        textView.setBackgroundColor(Color.LTGRAY);

        ll_students.addView(textView);

    }

    public void deleteStudent(View view){

        Log.i("deleteStudent", "to delete student");

        id = idET.getText().toString();
        name = nameET.getText().toString();
        age = ageET.getText().toString();
        gender = genderET.getText().toString();

        /*Object[] paras = new Object[4];

        String condition = " where 1=1 ";

        if(!"".equals(id)){
            paras[0]=id;
            condition += " and id like ?";
        }

        if(!"".equals(name)){
            paras[1]=name;
            condition += " and name like ?";
        }

        if(!"".equals(age)){
            paras[2]=age;
            condition += " and age like ?";
        }

        if(!"".equals(gender)){
            paras[3]=gender;
            condition += " and gender like ?";
        }

        String deleteAStudent = "delete from students "+condition;
        sqliteDatabase.execSQL(deleteAStudent,paras);*/

        String deleteAStudent = "delete from students where id like ?";
        sqliteDatabase.execSQL(deleteAStudent, new Object[]{id});

        Log.i("deleteStudent", "deleted student");
    }

    public void alterStudent(View view){

        Log.i("alterStudent", "to alter student");

        id = idET.getText().toString();
        name = nameET.getText().toString();
        age = ageET.getText().toString();
        gender = genderET.getText().toString();

        String alterAStudent = "update students set name = ?, age = ?, gender = ? where id like ?";
        sqliteDatabase.execSQL(alterAStudent, new Object[]{name, age, gender, id});

        Log.i("alterStudent", "altered student");

    }

    public void searchStudent(View view){

        Log.i("searchStudent", "to search student");


        id = idET.getText().toString();
        name = nameET.getText().toString();
        age = ageET.getText().toString();
        gender = genderET.getText().toString();

        String[] paras = new String[4];

        //String condition = " where 1=1 ";

        if (!"".equals(id)) {
            paras[0] = id;
            //condition += " and id like ?";
        } else {
            paras[0] = "%";
        }

        if (!"".equals(name)) {
            paras[1] = name;
            //condition += " and name like ?";
        } else {
            paras[1] = "%";
        }

        if (!"".equals(age)) {
            paras[2] = age;
            //condition += " and age like ?";
        } else {
            paras[2] = "%";
        }

        if (!"".equals(gender)) {
            paras[3] = gender;
            //condition += " and gender like ?";
        } else {
            paras[3] = "%";
        }

        //String searchAStudent = "select * from students "+condition;
        String searchAStudent = "select * from students where id like ? and name like ? and age like ? and gender like ?";
        Cursor cursor = sqliteDatabase.rawQuery(searchAStudent, paras);

        Log.i("searchStudent", "searched student");

        showStudents(cursor);
    }

    public void showStudents(Cursor cursor) {

        Log.i("showStudents", "to show students");

        Log.i("showStudents", "Before remove:" + String.valueOf(ll_students.getChildCount()));
        ll_students.removeAllViews();
        Log.i("showStudents", "After removeAllViews:" + String.valueOf(ll_students.getChildCount()));

        //ll_students.clearDisappearingChildren();
        //ll_students.removeAllViewsInLayout();
        //Log.i("showStudents", "After removeAllViewsInLayout:"+String.valueOf(ll_students.getChildCount()));

        students.removeAll(students);//This is the point.

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String gender = cursor.getString(3);
            students.add(new Student(id, name, age, gender));
        }

        for (Student student : students) {
            TextView textView = new TextView(this);

            textView.setText("学号：" + student.getId() + " 姓名：" + student.getName() + " 年龄：" + student.getAge() + " 性别：" + student.getGender());
            textView.setTextColor(Color.CYAN);
            textView.setTextSize(20); //只能通过像素去设置
            textView.setBackgroundColor(Color.LTGRAY);

            ll_students.addView(textView);
        }

        Log.i("showStudents", "showed students");

    }

    public void saveAllStudent(View view){

        Log.i("saveAllStudent", "to save students");

        Toast.makeText(this, "Nothing to do", Toast.LENGTH_SHORT).show();

        Log.i("saveAllStudent", "saved students");
    }

    public void restoreInfo(View view){

        Log.i("restoreInfo", "to show students");

        //ll_students.removeAllViews();

        Cursor cursor = sqliteDatabase.rawQuery("select * from students", null);

        Log.i("restoreInfo", "get students");

        showStudents(cursor);
    }


}

class Student {
    private String id;
    private String name;
    private String age;
    private String gender;

    public Student() {
    }

    public Student(String id, String name, String age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

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
