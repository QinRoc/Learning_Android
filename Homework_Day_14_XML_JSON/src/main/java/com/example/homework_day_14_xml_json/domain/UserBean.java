package com.example.homework_day_14_xml_json.domain;

import java.io.Serializable;

/**
 * Created by Roc on 2017/3/29.
 */

public class UserBean implements Serializable {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String gender;

    public UserBean() {
    }

    public UserBean(String username, String password, String email, String phone, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "username=" + username +
                "&password=" + password +
                "&email=" + email +
                "&phone=" + phone +
                "&gender=" + gender;
    }
}