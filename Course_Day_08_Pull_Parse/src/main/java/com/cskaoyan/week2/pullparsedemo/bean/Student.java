package com.cskaoyan.week2.pullparsedemo.bean;

/**
 * Created by zhao on 2017/3/21.
 */

public class Student {

    private String id ;
    private String name;
    private String gender;
    private String sclass;

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

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public Student() {
    }

    public Student(String id, String name, String gender, String sclass) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.sclass = sclass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", sclass='" + sclass + '\'' +
                '}';
    }
}
