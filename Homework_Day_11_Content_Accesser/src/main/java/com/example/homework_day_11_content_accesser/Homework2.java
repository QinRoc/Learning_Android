package com.example.homework_day_11_content_accesser;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Homework2 extends AppCompatActivity {

    private ContentResolver contentResolver;
    private ArrayList<User> users;
    private ArrayList<Goods> goodsList;
    private Uri usersUri;
    private Uri goodsUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);

        Log.i("onCreate", "oncreate run i");

        contentResolver = getContentResolver();
        //usersUri = Uri.parse("Content://com.roc.provider.auth/users");
        usersUri = Uri.parse("content://com.roc.provider.auth/users");
        goodsUri = Uri.parse("content://com.roc.provider.auth/goods");
    }

    public void usersQuery(View view) {

        Cursor query = contentResolver.query(usersUri,
                //new String[]{"id","name"},
                null,
                null,
                null,
                null);

        users = new ArrayList<>();
        while (query.moveToNext()) {
            users.add(new User(query.getInt(0), query.getString(1), query.getString(2)));
        }
        query.close();

        Log.i("usersQuery", users.toString());
        Toast.makeText(this, users.toString(), Toast.LENGTH_SHORT).show();
    }

    public void usersInsert(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 20);
        contentValues.put("name", "insert");
        contentValues.put("gender", "m");

        contentResolver.insert(usersUri, contentValues);

        Log.i("usersInsert", "增加成功");
        Toast.makeText(this, "增加成功", Toast.LENGTH_SHORT).show();
    }

    public void usersDelete(View view) {
        contentResolver.delete(usersUri, "id=?", new String[]{"9"});
        Log.i("usersDelete", "删除成功");
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    public void usersUpdate(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("gender", "f");

        contentResolver.update(usersUri, contentValues, "id=?", new String[]{"8"});

        Log.i("usersUpdate", "修改成功");
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
    }

    public void goodsQuery(View view) {

        Cursor query = contentResolver.query(goodsUri,
                //new String[]{"id","name"},
                null,
                null,
                null,
                null);

        goodsList = new ArrayList<>();
        while (query.moveToNext()) {
            goodsList.add(new Goods(query.getInt(0), query.getString(1), query.getInt(2)));
        }
        query.close();

        Log.i("goodsQuery", goodsList.toString());
        Toast.makeText(this, goodsList.toString(), Toast.LENGTH_SHORT).show();
    }

    public void goodsInsert(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 20);
        contentValues.put("name", "insert");
        contentValues.put("price", 100);

        contentResolver.insert(goodsUri, contentValues);

        Log.i("goodsInsert", "增加成功");
        Toast.makeText(this, "增加成功", Toast.LENGTH_SHORT).show();
    }

    public void goodsDelete(View view) {
        contentResolver.delete(goodsUri, "id=?", new String[]{"9"});
        Log.i("goodsDelete", "删除成功");
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    public void goodsUpdate(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("price", 1000);

        contentResolver.update(goodsUri, contentValues, "id=?", new String[]{"8"});

        Log.i("goodsUpdate", "修改成功");
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
    }

}

class User {
    private int id;
    private String name;
    private String gender;

    public User() {
    }

    public User(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class Goods {
    private int id;
    private String name;
    private int price;

    public Goods() {
    }

    public Goods(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
