package com.cskaoyan.day2.uicomponent;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String MyTAG = "onCreate";
    private TextView gender_text;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui);

        //必须在secontentView之后才能去findviewbyID
        gender_text = (TextView) findViewById(R.id.gender_text);
        RadioGroup rg = (RadioGroup) findViewById(R.id.gender_radiogroup);
        CheckBox cb_married = (CheckBox) findViewById(R.id.cb_married);
        editText = (EditText) findViewById(R.id.et_input);

        cb_married.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(MainActivity.this,"已婚",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"未婚",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId){

                    case R.id.male:
                        gender_text.setText("您选择了男");
                        Toast.makeText(MainActivity.this,"您选择了男",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.female:
                        gender_text.setText("您选择了女");
                        Toast.makeText(MainActivity.this,"您选择了女",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

       /* Log.d(MyTAG,"oncreate run d");
        Log.i(MyTAG,"oncreate run i");
        Log.e(MyTAG,"oncreate run e");
        Log.v(MyTAG,"oncreate run v");

        //先找到这个button，然后再给他通过代码去设置响应函数
        TextView v = (TextView) findViewById(R.id.textView );
        Button btn = (Button) findViewById(R.id.button1 );
        Button btn2 = (Button) findViewById(R.id.button2 );*/

         /*if (btn!=null){

            *//*  btn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      System.out.println("my button has benn clicked!");
                  }
              });*//*

           *//* MyBtnOnclickListener listener = new MyBtnOnclickListener();
            btn.setOnClickListener(listener);*//*
             btn.setOnClickListener(this);
             btn2.setOnClickListener(this);

            // System.out.println("view is "+v);
        }*/
    }

    //button控件设定响应函数的4种方式

    //方式1 ：直接写函数，然后在layout布局文件中指定onclick属性
    //响应函数的格式必须是public 必须没有返回值 void 里面必须要传一个View类型的参数
    public void myFunction(View v){
        System.out.println("hello,world");
    }

    //第二种方式
    //先找到这个button，然后再给他通过代码去设置响应函数

    //第三种方式：
    class MyBtnOnclickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            System.out.println("第三种设置响应的方式！");
        }
    }

    //第四种方式：
    //传入的view 实际上是当前点击的控件的一个引用。
    //它的作用有两个：
    // 1 可以很方便的使用当前的引用去获取当前控件的一些信息。比如状态，比如里面的一些
    // 用户输入的信息

    // 2：当多个view控件设置相同的响应函数的时候，可以根据view 去判断到底是哪个控件。
    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case  R.id.button1:

                buttion1Click();
                break;

            case  R.id.button2:
                buttion2Click();
                break;
        }
        //System.out.println("btn响应的第四种方法" +v);
    }


    public void    buttion1Click(){
        //System.out.println("btn响应的第四种方法 我是buttion1的响应函数"  );
        Toast mytoast = Toast.makeText(MainActivity.this,"我是button1",Toast.LENGTH_SHORT);
        mytoast.show();
    }

    public void    buttion2Click(){
        //System.out.println("btn响应的第四种方法 我是buttion2的响应函数"  );
        makeText(MainActivity.this,"我是button2",Toast.LENGTH_LONG).show();
    }


    public  void submit(View v){

        /*if (editText!=null){
           String inputtext =   editText.getText().toString();
            Toast.makeText(this,inputtext,Toast.LENGTH_SHORT).show();
        }*/

        ImageView  iv = (ImageView) findViewById(R.id.iv_pic);
        iv.setImageResource(R.drawable.c5);
    }
}
