package com.theyestech.yestechpremium.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.theyestech.yestechpremium.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInformationFragment extends Fragment {
    private View view;
    private Context context;
    private String usertype;
    private TextView tv_studid;
    private EditText et_StudentId;
    public UserInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_information, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getActivity().getIntent();
        usertype = intent.getStringExtra("USERTYPE");
        initializeUI();
    }

    private void initializeUI(){
        tv_studid = view.findViewById(R.id.tv_studid);
        et_StudentId = view.findViewById(R.id.et_StudentId);
        if(usertype.equals("educator")){
            tv_studid.setVisibility(View.GONE);
            et_StudentId.setVisibility(View.GONE);
        }
    }
}
