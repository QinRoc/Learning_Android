package com.cskaoyan.week3.networklibdemoapp;

import java.util.ArrayList;

/**
 * Created by zhao on 2017/3/29.
 */

public class Categories {

    int retcode;
    ArrayList<Data> data;
    //ArrayList<Integer> extend ;

    class Data {
        int id;
        String title;
        int type;
        ArrayList<Children> children;

        class Children {
            int id;
            String title;
            int type;
            String url;
        }
    }
}
