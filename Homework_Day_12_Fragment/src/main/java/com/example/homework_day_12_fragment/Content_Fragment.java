package com.example.homework_day_12_fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Content_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Content_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content_Fragment extends Fragment {

    static final String ARG_PARAM = "param";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Serializable content;

    public Content_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Content_Fragment.
     */
    public static Content_Fragment newInstance(String param1, String param2) {

        Log.i("CFragment newInstance", "newInstance");

        Content_Fragment fragment = new Content_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("CFragment onCreate", "onCreate");

        Log.i("CFragment onCreate", "savedInstanceState:" + savedInstanceState);
        Log.i("CFragment onCreate", "getArguments():" + getArguments());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("CFragment onCreateView", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        Log.i("CFragment onCreateView", "savedInstanceState:" + savedInstanceState);
        //if (savedInstanceState != null) {
        if (getArguments() != null) {
            Log.i("CFragment onCreateView", "content");
            //content = savedInstanceState.getSerializable(ARG_PARAM);
            content = getArguments().getSerializable(ARG_PARAM);
            Log.i("CFragment onCreateView", content.toString());
            TextView text = (TextView) view.findViewById(R.id.text);
            text.setText(content.toString());
        }

        // Inflate the layout for this fragment
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i("CFragment onAttach", "onAttach");

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
