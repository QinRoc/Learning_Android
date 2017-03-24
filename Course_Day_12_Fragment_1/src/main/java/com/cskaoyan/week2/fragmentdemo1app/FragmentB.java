package com.cskaoyan.week2.fragmentdemo1app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhao on 2017/3/25.
 */

public class FragmentB extends Fragment {


    //onCreateView
    //当这个fragment要创建view（用来显示的视图的时候去掉用）
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /*Button button = new Button(getActivity());
        button.setText("i am rigth button !");*/

        View view = inflater.inflate(R.layout.fragmentb, container);

        return view;  //super.onCreateView(inflater, container, savedInstanceState);
    }
}
