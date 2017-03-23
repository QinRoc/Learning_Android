package com.cskaoyan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhao on 2017/3/16.
 */
// Parcelable protocol requires a Parcelable.Creator object called
// CREATOR on class com.cskaoyan.bean.Student
public class Student implements Parcelable{

    int age ;
    String name;

    protected Student(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public Student() {
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {

        //根据parcel去创建
        @Override
        public Student createFromParcel(Parcel in) {
            /*Student student = new Student();
            int i = in.readInt();
            String s = in.readString();
            student.setAge(i);
            student.setName(s);

            return  student;*/

            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //该方法会有一个Parcel类型的参数（包裹）
    //writeToParcel
    //该方法是系统自动调用的，所以系统会传递一个空的dest
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
    }
}
