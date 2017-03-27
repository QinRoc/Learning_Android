package com.cskaoyan.week2.fragmentdemo2app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        Button button1 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
                FragmentA fragmentA = new FragmentA();
                addFramgent(fragmentA);
                break;

            case R.id.button2:
                FragmentB fragmentb = new FragmentB();
                addFramgent(fragmentb);
                break;
        }
    }

    private void addFramgent(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FrameLayout framgent = (FrameLayout) findViewById(R.id.framgent);
        //让它自己把内部的子控件清空一下
        //Call this method to remove all child views from the ViewGroup.
        framgent.removeAllViews();

        //第一个参数是contianer 就是放fragment的地方
        //第二个参数是要替换的fragment
        fragmentTransaction.add(R.id.framgent, fragment);

        fragmentTransaction.commit();
    }
}
