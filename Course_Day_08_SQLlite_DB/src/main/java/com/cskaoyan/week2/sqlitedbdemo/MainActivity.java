package com.cskaoyan.week2.sqlitedbdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SqliteDemo";
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = createDB();
    }

    public SQLiteDatabase createDB(){

        //新建数据库
        //如果有数据库就打开，如果没有呢 就创建；

        // File file = new File(getFilesDir(),"mypro.db");

        String path="/data/data/"+getPackageName()+"/mypro.db";

        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path, null);

        //新建表
        String createTableSql = "create table if not exists student (id integer,name varchar(20));";
        sqLiteDatabase.execSQL(createTableSql);

        return sqLiteDatabase;
    }

    //使用原始的sql语句进行增删改查
    public void query(View v){

        if (db!=null){

            Cursor cursor = db.rawQuery("select * from studetn where id = ?;", new String[]{"2"});

            while(cursor.moveToNext()){

                //sqlite不支持使用列名去拿
                //mysql数据库的结果集 拿第一列传 1

                int id_index = cursor.getColumnIndexOrThrow("id");

                int id = cursor.getInt(id_index);

                String string = cursor.getString(1);

                Log.i(TAG,"id="+id +" name =" +string);

            }

            cursor.close();
        }
    }

    public void insert(View v){

        int id = 200;
        String name ="peter";

        if (db!=null){
            //db.execSQL("insert into studetn values ("+id+",'"+name+"');");
            //db.exe
            db.execSQL("insert into studetn values(?,?);",new Object[]{ id,name });
        }
    }

    public void update(View v){

        if (db!=null){
            db.execSQL("update studetn set name = ? where id = ?;", new Object[]{"张三丰",1});
        }
    }

    public void delete(View v){

        if (db!=null){
            db.execSQL("delete from studetn where id = ?;", new Object[]{1});
        }
    }

    //使用Android提供的API来进行增删改查
    public void query2(View v){

        if (db!=null){

            //query(String table, 表名
            // String[] columns,  列名
            // String selection,  查询条件
            // String[] selectionArgs, 查询条件的参数
            // String groupBy,
            // String having,
            // String orderBy)

            Cursor cursor = db.query("studetn",
                    new String[]{"id", "name"},
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()){

                int anInt = cursor.getInt(0);
                String string = cursor.getString(1);

                Log.i(TAG,"id=" + anInt +" name =" +string);
            }
        }
    }

    public void update2(View v){

        if (db!=null){

            //update(String table,
            // ContentValues values,
            // String whereClause,
            // String[] whereArgs)
             ContentValues contentValues= new ContentValues();
             contentValues.put("name","lisi");
             db.update("studetn",contentValues,"id = ?", new String[]{"2"});
        }
    }


    public void delete2(View v){

        if (db!=null){
            db.delete("studetn","id = ?",new String[]{"100"} );
        }
    }


    public void insert2(View v){

        if (db!=null){

            //insert(String table,
            // String nullColumnHack, 暂时不管 null
            // ContentValues values)

            // （300，‘张三丰’）;

            ContentValues contentValues = new ContentValues();
            contentValues.put("id",300);
            contentValues.put("name","张三丰");

            // insert into student (id ,name ) values ( ? ,?)  ;
            // insert into student  (  "+nullColumnHack + ") VALUES (NULL");
            db.insert("studetn", "name", null );
            /*
            SQL doesn't allow inserting a completely empty row without
            naming at least one column name.  If your provided <code>values</code> is
            empty, no column names are known and an empty row can't be inserted.
            If not set to null, the <code>nullColumnHack</code> parameter
            provides the name of nullable column name to explicitly insert a NULL into
            in the case where your <code>values</code> is empty.
            */
        }
    }
}
