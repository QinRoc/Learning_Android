package com.roc.homework_day_03_ui_component.domain;

/**
 * Created by Peng on 2017/3/16.
 */

public class UserBean {
    private String username;
    private String password;
    private String email;
    private String gender;
    private String phone;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserBean() {
        super();
    }

    public UserBean(String username, String password, String email, String gender, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
