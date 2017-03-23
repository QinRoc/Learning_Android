package com.roc.homework_day_07_file_system;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Homework2_Register extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private EditText repasswordET;
    private EditText emailET;
    private EditText phoneET;
    private CheckBox agreementET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2__register);

        Log.i("register","oncreate run i");

        usernameET = (EditText) findViewById(R.id.username);
        passwordET = (EditText) findViewById(R.id.password);
        repasswordET = (EditText) findViewById(R.id.repassword);
        emailET = (EditText) findViewById(R.id.email);
        phoneET = (EditText) findViewById(R.id.phone);
        agreementET = (CheckBox) findViewById(R.id.agreement);

        showUserRegisterInfo();
    }

    public void showUserRegisterInfo(){
        SharedPreferences sp = getSharedPreferences("base64", MODE_PRIVATE);
        if(sp!=null){
            String userString = sp.getString("user","");
            if(!"".equals(userString)){//To avoid EOFException
                byte[] base64User = Base64.decode(userString,Base64.DEFAULT);
                ByteArrayInputStream bais = new ByteArrayInputStream(base64User);
                try {
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    UserBean user = (UserBean) ois.readObject();
                    usernameET.setText(user.getUsername());
                    emailET.setText(user.getEmail());
                    phoneET.setText(user.getPhone());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void register(View view){
        String username;
        String password;
        String repassword;
        String email;
        String phone;

        UserBean user = new UserBean();

        if(agreementET.isChecked()){
            username = usernameET.getText().toString();
            password = passwordET.getText().toString();
            repassword = repasswordET.getText().toString();
            email = emailET.getText().toString();
            phone = phoneET.getText().toString();

            if(username==null||password==null||repassword==null
                    ||email==null||phone==null){
                Toast.makeText(this, "请填写所有注册信息", Toast.LENGTH_SHORT).show();
            }else{
                if(password.equals(repassword)){
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setPhone(phone);

                    /*SharedPreferences sp = new SharedPreferences() {
                    }*/
                    SharedPreferences sharedPreferences = getSharedPreferences("base64", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(baos);
                        oos.writeObject(user);
                        String base64User = Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);
                        editor.putString("user",base64User);
                        editor.commit();
                        oos.close();
                        baos.close();
                        Toast.makeText(this, "保存注册信息成功", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "保存注册信息失败！！！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "两次输入的密码需要一致", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "请阅读并同意协议", Toast.LENGTH_SHORT).show();
        }
    }
}
