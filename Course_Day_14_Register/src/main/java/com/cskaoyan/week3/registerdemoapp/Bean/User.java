package com.cskaoyan.week3.registerdemoapp.Bean;

public class User {

    int id;
    String username;
    String email;
    String gender;
    String password;

    public User(int id, String username, String email, String gender,
                String password) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email
                + ", gender=" + gender + ", password=" + password + "]";
    }
}
