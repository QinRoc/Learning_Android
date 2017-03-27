package com.cskaoyan.week2.fragmentdemo3app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhao on 2017/3/25.
 */

public class FragmentContent extends Fragment {

    private TextView news_title;
    private ImageView news_pic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_fragement, container);

        news_title = (TextView) view.findViewById(R.id.news_title);
        news_pic = (ImageView) view.findViewById(R.id.news_pic);

        return view;
    }


    //给外界其他的fagment暴露的API，用于让其他Fragment修改我的标题
    public void setNews_title(String title) {
        news_title.setText(title);
    }

    //给外界其他的fagment暴露的API，用于让其他Fragment修改我的图片
    public void setNews_pic(int res_id) {
        news_pic.setImageResource(res_id);
    }
}
