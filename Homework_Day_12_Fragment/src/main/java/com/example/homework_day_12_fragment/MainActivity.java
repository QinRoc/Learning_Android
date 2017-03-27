package com.example.homework_day_12_fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Left_Fragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("onCreate", "oncreate run i");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Left_Fragment left = new Left_Fragment();
        fragmentTransaction.add(R.id.fragment_container, left);
        fragmentTransaction.commit();
    }


    @Override
    public void onListFragmentInteraction(Content.ContentItem item) {

        Log.i("MainActivity onLFI", "click item");

        //Content_Fragment content = getFragmentManager().findFragmentById(R.id.);
        Content_Fragment content = new Content_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(Content_Fragment.ARG_PARAM, item);
        content.setArguments(args);

        //getSupportFragmentManager().findFragmentById();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Content_Fragment content = new Content_Fragment();
        fragmentTransaction.replace(R.id.fragment_container, content);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
