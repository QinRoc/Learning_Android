package com.roc.homework_day_04_activity.com.roc.domain;

import java.io.Serializable;

/**
 * Created by Peng on 2017/3/17.
 */

public class RPBean implements Serializable {
    private  String name;
    private int rp;
    private String rpText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public String getRpText() {
        return rpText;
    }

    public void setRpText(String rpText) {
        this.rpText = rpText;
    }

    public RPBean() {
    }

    public RPBean(String name, int rp, String rpText) {
        this.name = name;
        this.rp = rp;
        this.rpText = rpText;
    }

    @Override
    public String toString() {
        return "RPBean{" +
                "name='" + name + '\'' +
                ", rp=" + rp +
                ", rpText='" + rpText + '\'' +
                '}';
    }
}
