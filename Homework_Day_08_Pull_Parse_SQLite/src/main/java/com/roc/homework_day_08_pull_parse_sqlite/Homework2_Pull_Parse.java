package com.roc.homework_day_08_pull_parse_sqlite;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Homework2_Pull_Parse extends AppCompatActivity {


    private File externamStorageDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2__pull__parse);

        externamStorageDirectory = Environment.getExternalStorageDirectory();

        Log.i("Parse","oncreate run i");
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "需要读写SD卡以拷贝其上的文件", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2);
            }
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==2){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }else{
                Toast.makeText(this, "请授予读写SD卡的权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
    使用pull解析解析users.xml文件。
    要求可以将xml文件里的user信息保存到一个user集合里面。
    */
    public void pullParseUsers(View view){

        Log.i("Parse","准备解析XML");
        Toast.makeText(this, "准备解析XML", Toast.LENGTH_SHORT).show();
        
        String externalStorageState = Environment.getExternalStorageState();
        if (!externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "未装载SD卡，无法完成操作", Toast.LENGTH_SHORT).show();
            return;
        }

        //File usersXML = new File(Environment.getExternalStorageDirectory()+"/Download/users.xml");
        //File fileFrom =new File(externamStorageDirectory+"/DCIM/100ANDRO/2.jpg");
        File usersXML = new File(externamStorageDirectory+"/Download/users.xml");
        
        XmlPullParser parser = Xml.newPullParser();
        Log.i("Parse","获取XML");

        ArrayList<User> users = new ArrayList<User>();
        User user = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(usersXML);
            parser.setInput(fileInputStream,"utf-8");
            Log.i("Parse","解析XML");
            int read = parser.next();
            while(read!=XmlPullParser.END_DOCUMENT){

                switch (read){
                    case XmlPullParser.START_TAG:
                        if ("user".equals(parser.getName())){
                            user = new User();
                            Log.i("User","new User()");
                            //user.setUsername(parser.getAttributeValue(1));//index start from 0
                            user.setUsername(parser.getAttributeValue(0));
                            user.setPassword(parser.getAttributeValue(null,"password"));
                            user.setEmail(parser.getAttributeValue(null,"email"));
                        }/*else if("username".equals(parser.getName())){
                            user.setUsername(parser.nextText());
                            Log.i("User","+username");
                        }else if("password".equals(parser.getName())){
                            user.setPassword(parser.nextText());
                            Log.i("User","+password");
                        }else if("email".equals(parser.getName())){
                            user.setEmail(parser.nextText());
                            Log.i("User","+email");
                        }*/
                        break;

                    case XmlPullParser.END_TAG:
                        if("user".equals(parser.getName())){
                            users.add(user);
                            Log.i("User",user.toString());
                            user=null;
                        }
                        break;
                }

                read = parser.next();
            }

            Log.i("Parse","解析XML完毕");
            Toast.makeText(this, "解析完毕XML", Toast.LENGTH_SHORT).show();

            TextView showUsers = (TextView) findViewById(R.id.showUsers);
            showUsers.setText(users.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    利用customer.java这个 javabean生成一个customer的数组。
    使用xml序列化器将该数组生成一个xml文件。

    private String id;//逻辑主键
	private String name;
	private String gender;//数据库中：1 男 0 女
	private String birthday;
	private String cellphone;//电话号码
	private String email;//邮箱
	private String hobby;//爱好： 吃饭,睡觉,学java
	private String type;//客户类型：普通客户 VIP
	private String description;//简介
    */
    public void saveCustomersToXML(View view){

        Log.i("Save Customers","准备生成Customers");
        Toast.makeText(this, "准备生成Customers", Toast.LENGTH_SHORT).show();

        CustomerBean customer1 = new CustomerBean("1","first","1","20130512","15845612378","wqwe@163.com","吃饭","VIP","个人介绍");
        CustomerBean customer2 = new CustomerBean("2","second","0","20130512","15845612378","wqwe@163.com","吃饭","VIP","个人介绍");
        CustomerBean[] customers = new CustomerBean[]{customer1,customer2};

        Log.i("Save Customers","准备保存Customers");
        Toast.makeText(this, "准备保存Customers", Toast.LENGTH_SHORT).show();
        String externalStorageState = Environment.getExternalStorageState();
        if (!externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "未装载SD卡，无法完成操作", Toast.LENGTH_SHORT).show();
            return;
        }

        XmlSerializer xmlSerializer = Xml.newSerializer();
        File customersXML = new File(getFilesDir(),"Customers.xml");
        Log.i("Save Customers","保存Customers");

        try {
            xmlSerializer.setOutput(new FileOutputStream(customersXML),"utf-8");
            xmlSerializer.startDocument("utf-8",true);
            xmlSerializer.startTag(null,"customers");
            for (CustomerBean customer:
                 customers) {
                xmlSerializer.startTag(null,"customer");
                    xmlSerializer.startTag(null,"id");
                        xmlSerializer.text(customer.getId());
                    xmlSerializer.endTag(null,"id");

                    xmlSerializer.startTag(null,"name");
                        xmlSerializer.text(customer.getName());
                    xmlSerializer.endTag(null,"name");

                    xmlSerializer.startTag(null,"gender");
                        xmlSerializer.text(customer.getGender());
                    xmlSerializer.endTag(null,"gender");

                    xmlSerializer.startTag(null,"birthday");
                        xmlSerializer.text(customer.getBirthday());
                    xmlSerializer.endTag(null,"birthday");

                    xmlSerializer.startTag(null,"cellphone");
                        xmlSerializer.text(customer.getCellphone());
                    xmlSerializer.endTag(null,"cellphone");

                    xmlSerializer.startTag(null,"email");
                        xmlSerializer.text(customer.getEmail());
                    xmlSerializer.endTag(null,"email");

                    xmlSerializer.startTag(null,"hobby");
                        xmlSerializer.text(customer.getHobby());
                    xmlSerializer.endTag(null,"hobby");

                    xmlSerializer.startTag(null,"type");
                        xmlSerializer.text(customer.getType());
                    xmlSerializer.endTag(null,"type");

                    xmlSerializer.startTag(null,"description");
                        xmlSerializer.text(customer.getDescription());
                    xmlSerializer.endTag(null,"description");
                xmlSerializer.endTag(null,"customer");
            }
            xmlSerializer.endTag(null,"customers");
            xmlSerializer.endDocument();

            Log.i("Save Customers","保存完毕");
            Toast.makeText(this, "保存完毕", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSavedCustomers(View vies){
        File customersXML = new File(getFilesDir(),"Customers.xml");
        XmlPullParser parser = Xml.newPullParser();
        Log.i("","");

        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        CustomerBean customer = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(customersXML);
            parser.setInput(fileInputStream,"utf-8");
            Log.i("","");
            int read = parser.next();
            while(read!=XmlPullParser.END_DOCUMENT){

                switch (read){
                    case XmlPullParser.START_TAG:
                        if ("customer".equals(parser.getName())){
                            customer = new CustomerBean();
                        }else if("id".equals(parser.getName())){
                            customer.setId(parser.nextText());
                        }else if("name".equals(parser.getName())){
                            customer.setName(parser.nextText());
                        }else if("gender".equals(parser.getName())){
                            customer.setGender(parser.nextText());
                        }else if("birthday".equals(parser.getName())){
                            customer.setBirthday(parser.nextText());
                        }else if("cellphone".equals(parser.getName())){
                            customer.setCellphone(parser.nextText());
                        }else if("email".equals(parser.getName())){
                            customer.setEmail(parser.nextText());
                        }else if("hobby".equals(parser.getName())){
                            customer.setHobby(parser.nextText());
                        }else if("type".equals(parser.getName())){
                            customer.setType(parser.nextText());
                        }else if("description".equals(parser.getName())){
                            customer.setDescription(parser.nextText());
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if("customer".equals(parser.getName())){
                            customers.add(customer);
                            customer=null;
                        }
                        break;
                }

                read = parser.next();
            }

            Log.i("Customers","解析XML完毕");
            Toast.makeText(this, "解析完毕XML", Toast.LENGTH_SHORT).show();

            TextView showUsers = (TextView) findViewById(R.id.showUsers);
            showUsers.setText(customers.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User{
    private String username;
    private String password;
    private String email;

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

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
