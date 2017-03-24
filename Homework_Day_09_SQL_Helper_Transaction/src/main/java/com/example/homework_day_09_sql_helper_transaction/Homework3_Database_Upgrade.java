package com.example.homework_day_09_sql_helper_transaction;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Homework3_Database_Upgrade extends AppCompatActivity {

    private EditText idET;
    private EditText nameET;
    private EditText genderET;
    private EditText classET;
    private EditText majorET;

    private String id;
    private String name;
    private String gender;
    private String studentClass;
    private String major;

    private SQLiteDatabase sqLiteDatabase;

    private ArrayList<Student> students;
    private int versioncode;
    private StudentOpenHelper studentOpenHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3__database__upgrade);

        Log.i("onCreate", "on create run i");
        //final int currentBuild = AppUtil.getVersionCode(this);
        PackageManager pm = this.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //String versionName = pi.versionName;
        versioncode = pi.versionCode;

        if (versioncode == 1) {
            idET = (EditText) findViewById(R.id.id1);
            nameET = (EditText) findViewById(R.id.name1);
            genderET = (EditText) findViewById(R.id.gender1);
            classET = (EditText) findViewById(R.id.class1);
            studentOpenHelper = new StudentOpenHelper(this, "Students.db", null, 1);

        } else if (versioncode == 2) {
            idET = (EditText) findViewById(R.id.id2);
            nameET = (EditText) findViewById(R.id.name2);
            genderET = (EditText) findViewById(R.id.gender2);
            classET = (EditText) findViewById(R.id.class2);
            majorET = (EditText) findViewById(R.id.major2);
            studentOpenHelper = new StudentOpenHelper(this, "Students.db", null, 2);
        }

        //StudentOpenHelper studentOpenHelper = new StudentOpenHelper(this,"Students.db",null,1);
        sqLiteDatabase = studentOpenHelper.getWritableDatabase();

        students = new ArrayList<>();
    }

    public void add1(View view) {

        //id = Integer.parseInt(idET.getText().toString());
        id = idET.getText().toString();
        name = nameET.getText().toString();
        gender = genderET.getText().toString();
        studentClass = classET.getText().toString();

        sqLiteDatabase.execSQL("insert into students values (?,?,?,?)", new Object[]{id, name, gender, studentClass});
        Toast.makeText(this, "增加成功", Toast.LENGTH_SHORT).show();
    }

    public void query1(View view) {

        students.removeAll(students);

        id = idET.getText().toString();
        name = nameET.getText().toString();
        gender = genderET.getText().toString();
        studentClass = classET.getText().toString();

        /*if(id!=null&&!"".equals(id)){//parseInt("")=?

        }else{
            id="%";
        }*/
        if (id == null || "".equals(id)) {//parseInt("")=?
            id = "%";
        }
        if (name == null || "".equals(name)) {//parseInt("")=?
            name = "%";
        }
        if (gender == null || "".equals(gender)) {//parseInt("")=?
            gender = "%";
        }
        if (studentClass == null || "".equals(studentClass)) {//parseInt("")=?
            studentClass = "%";
        }

        Cursor cursor = sqLiteDatabase.rawQuery("select * from students where id like ? and name like ? and gender like ? and studentClass like ?",
                new String[]{id, name, gender, studentClass});

        while (cursor.moveToNext()) {
            students.add(new Student(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), ""));
        }
        cursor.close();

        Log.i("query1", students.toString());
        Toast.makeText(this, students.toString(), Toast.LENGTH_SHORT).show();
    }

    public void add2(View view) {

        if (versioncode != 2) {
            Toast.makeText(this, "版本过低", Toast.LENGTH_SHORT).show();
            return;
        }

        id = idET.getText().toString();
        name = nameET.getText().toString();
        gender = genderET.getText().toString();
        studentClass = classET.getText().toString();
        major = majorET.getText().toString();

        sqLiteDatabase.execSQL("insert into students values (?,?,?,?,?)", new Object[]{id, name, gender, studentClass, major});
    }

    public void query2(View view) {

        if (versioncode != 2) {
            Toast.makeText(this, "版本过低", Toast.LENGTH_SHORT).show();
            return;
        }

        students.removeAll(students);

        String query = "";

        id = idET.getText().toString();
        name = nameET.getText().toString();
        gender = genderET.getText().toString();
        studentClass = classET.getText().toString();
        major = majorET.getText().toString();

        if (id == null || "".equals(id)) {//parseInt("")=?
            id = "%";
        }
        if (name == null || "".equals(name)) {//parseInt("")=?
            name = "%";
        }
        if (gender == null || "".equals(gender)) {//parseInt("")=?
            gender = "%";
        }
        if (studentClass == null || "".equals(studentClass)) {//parseInt("")=?
            studentClass = "%";
        }

        if (major == null || "".equals(major)) {//parseInt("")=?
            major = "%";
            query = "select * from students where id like ? and name like ? and gender like ? and studentClass like ?";
            cursor = sqLiteDatabase.rawQuery(query,
                    new String[]{id, name, gender, studentClass});
        } else {
            query = "select * from students where id like ? and name like ? and gender like ? and studentClass like ? and major like ?";
            cursor = sqLiteDatabase.rawQuery(query,
                    new String[]{id, name, gender, studentClass, major});
        }


       /* cursor = sqLiteDatabase.rawQuery(query,
                new String[]{id, name, gender, studentClass,major});*/

        while (cursor.moveToNext()) {
            students.add(new Student(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
        cursor.close();

        Log.i("query2", students.toString());
        Toast.makeText(this, students.toString(), Toast.LENGTH_SHORT).show();
    }


}

class Student {
    private String id;
    private String name;
    private String gender;
    private String studentClass;
    private String major;

    public Student() {
    }

    public Student(String id, String name, String gender, String studentClass, String major) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.studentClass = studentClass;
        this.major = major;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
