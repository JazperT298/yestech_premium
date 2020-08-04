package com.theyestech.yestechpremium.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.theyestech.yestechpremium.R;
import com.theyestech.yestechpremium.activities.AddEducatorActivity;
import com.theyestech.yestechpremium.activities.AddStudentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment {
    private View view;
    private Context context;

    private TextView tv_menuHeader;
    private ImageView iv_Search, iv_AddStudent, iv_Back, iv_Close;
    private TextInputEditText et_SearchUser;
    private SwipeRefreshLayout swipe_StudentList;
    private ConstraintLayout indicator_empty_record;
    private RecyclerView rv_StudentList;
    public StudentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_list, container, false);
        context = getContext();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
        tv_menuHeader = view.findViewById(R.id.tv_menuHeader);
        iv_Search = view.findViewById(R.id.iv_Search);
        iv_AddStudent = view.findViewById(R.id.iv_AddStudent);
        iv_Back = view.findViewById(R.id.iv_Back);
        iv_Close = view.findViewById(R.id.iv_Close);
        et_SearchUser = view.findViewById(R.id.et_SearchUser);
        swipe_StudentList = view.findViewById(R.id.swipe_StudentList);
        rv_StudentList = view.findViewById(R.id.rv_StudentList);
        et_SearchUser.requestFocus();
        iv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_menuHeader.setVisibility(View.GONE);
                iv_Search.setVisibility(View.GONE);
                iv_AddStudent.setVisibility(View.GONE);
                et_SearchUser.setVisibility(View.VISIBLE);
                iv_Back.setVisibility(View.VISIBLE);
                iv_Close.setVisibility(View.VISIBLE);
            }
        });
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_menuHeader.setVisibility(View.VISIBLE);
                iv_Search.setVisibility(View.VISIBLE);
                iv_AddStudent.setVisibility(View.VISIBLE);
                et_SearchUser.setVisibility(View.GONE);
                iv_Back.setVisibility(View.GONE);
                iv_Close.setVisibility(View.GONE);
            }
        });
        iv_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SearchUser.setText("");
            }
        });
        iv_AddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddStudentActivity.class);
                intent.putExtra("USERTYPE", "student");
                startActivity(intent);
            }
        });
    }
}
