package com.cskaoyan.week2.fragmentdemo3app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2017/3/25.
 */

public class FragmentLeft extends Fragment {

    private static final String TAG = "FragmentLeft";

    private ListView title_listview;

    private List<String> title_stringList;

    private int[] resource_ids = {
            R.drawable.c1,
            R.drawable.c2,
            R.drawable.c3,
            R.drawable.c4,
            R.drawable.c5,
            R.drawable.c6,
            R.drawable.c7,
            R.drawable.c8
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.left_fragment, container);

        title_listview = (ListView) view.findViewById(R.id.title_listview);

        initData();

        title_listview.setAdapter(new MyListAdpater());

        title_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Activity activity = getActivity();
                FragmentManager fragmentManager = activity.getFragmentManager();
                final FragmentContent fragment_content =
                        (FragmentContent) fragmentManager.findFragmentById(R.id.fragment_content);

                String title = title_stringList.get(position);

                fragment_content.setNews_title(title);
                fragment_content.setNews_pic(resource_ids[position % 8]);
            }
        });

        Log.i(TAG, "onCreateView");

        return view;
    }

    private void initData() {
        title_stringList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            title_stringList.add("标题" + i);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
    }

    class MyListAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            return title_stringList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tv = null;
            if (convertView == null) {
                tv = new TextView(getContext());
            } else {
                tv = (TextView) convertView;
            }

            tv.setText(title_stringList.get(position));

            tv.setTextSize(24);
            //tv.setBackgroundColor(Color.YELLOW);
            return tv;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
