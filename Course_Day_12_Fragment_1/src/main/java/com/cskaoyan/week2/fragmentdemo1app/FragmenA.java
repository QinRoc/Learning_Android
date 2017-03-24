package com.cskaoyan.week2.fragmentdemo1app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zhao on 2017/3/25.
 */

public class FragmenA extends Fragment implements View.OnClickListener {

    //onCreateView
    //当这个fragment要创建view（用来显示的视图的时候去掉用）
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /*TextView textView = new TextView(getActivity());
        textView.setText("i am left !");*/

        View view = inflater.inflate(R.layout.fragmenta, container);

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);

        return view;  //super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "button clicked!", Toast.LENGTH_SHORT).show();
    }
}
