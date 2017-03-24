package com.cskaoyan.week2.listviewdemoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


//listview的优化

//优化1 ： 每一屏最多能显示多少个view，就先创建可以显示那么多个的view，不需要去把全部的view创建出来。
//优化2 ： view复用，把滑出去的view 保存起来，等一会需要创建的时候直接拿过来复用。（XX池）除了节省内存之前，还节省运行时间
//优化3 ： 提高getView()的执行速度。把这个api里的耗时操作都改进。findViewById() 就是耗时操作。
//我们把之前需要findviewbyID的子控件 保存到一个holder里，等到以后复用的时候，直接去类里拿，就不用 findViewById

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //List<String>  data;
    List<Student> data;

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //R.layout.activity_main
        //在当前Activity内调用这个findViewById 默认在R.layout.activity_main
        listview = (ListView) findViewById(R.id.listview);

        // 1.准备ListView要显示的数据 ；（通常使用 数组 或 集合 保存数据）；
        initData();

        // 2.构建适配器 ， 简单地来说， 适配器就是 Item数组 ， 动态数组 有多少元素就生成多少个Item；
        //MyListAdapter
        MyListAdapter adapter = new MyListAdapter();

        // 3.把 适配器 添加到ListView,并显示出来。
        listview.setAdapter(adapter);

        //点击listview的某一行的时候，可以给listview设定一个onclickListener

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //当listview中的某一个item被点击到的时候执行
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0 || position == data.size() + 1) {
                    return;
                }

                //Toast.makeText(MainActivity.this, "item clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivityActivity.class);
                Student student = data.get(position - 1);
                intent.putExtra("stu", student);
                startActivity(intent);
            }
        });

        //增加头布局
        TextView tvheader = new TextView(this);
        tvheader.setText("I am header!");
        listview.addHeaderView(tvheader);

        //增加尾布局
        TextView tvFooter = new TextView(this);
        tvFooter.setText("I am footer!");
        listview.addFooterView(tvFooter);

    }

    //初始化list里要显示的数据
    private void initData() {
        data = new ArrayList<Student>();

        MyDataBaseOpenHelper helper = new MyDataBaseOpenHelper(this, "student.db", null, 2);
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery("select * from stu;", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);

            Student student = new Student(id, name, age);

            data.add(student);
        }
        cursor.close();

        /*for (int i=0;i<20;i++){
            data.add("listdata"+i);
        }*/
    }

    class MyListAdapter extends BaseAdapter {

        //获取数量:获取数据的总数
        @Override
        public int getCount() {
            return data.size();
        }

        //getView 系统回调，通过这方法去拿一个view。
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /*TextView textView = new TextView(MainActivity.this);
            //textView.setText("hello,world "+position);
            Student student = data.get(position);
            textView.setText( student.toString() );*/

            Student student = data.get(position);

            //根据布局文件去产生一个复杂的viewgroup对象
            View view = null;

            if (convertView == null) {
                //访问磁盘 （慢）
                view = View.inflate(MainActivity.this, R.layout.list_item, null);
                //这里应该从R.layout.list_item这里找才可以找到
                //view.findViewById()
                TextView tv1 = (TextView) view.findViewById(R.id.tv1);
                TextView tv2 = (TextView) view.findViewById(R.id.tv2);
                tv1.setText(student.getName());
                tv2.setText(student.getId() + "");
                Holder holder = new Holder();
                holder.tv1 = tv1;
                holder.tv2 = tv2;
                //把holder挂到view上
                view.setTag(holder);
            } else {
                view = convertView;
                Object tag = view.getTag();
                if (tag instanceof Holder) {
                    Holder holer = (Holder) tag;

                    //TextView tv1 =(TextView) view.findViewById(tv1);
                    //TextView tv2 =(TextView) view.findViewById(R.id.tv2);
                    //tv1.setText(student.getName());
                    //tv2.setText(student.getId()+"");
                    holer.tv1.setText(student.getName());
                    holer.tv2.setText(student.getId() + "");
                }
            }

            if (convertView == null)
                Log.i(TAG, "position=" + position + "view =" + view + "convertView=null");
            else
                Log.i(TAG, "position=" + position + "view =" + view + "convertView=" + convertView);

            return view;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        class Holder {
            TextView tv1;
            TextView tv2;
        }

    }

}
