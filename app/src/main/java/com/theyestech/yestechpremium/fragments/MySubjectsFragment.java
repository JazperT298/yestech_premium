package com.theyestech.yestechpremium.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.theyestech.yestechpremium.R;
import com.theyestech.yestechpremium.activities.AddEducatorActivity;
import com.theyestech.yestechpremium.activities.ManageSubjectActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySubjectsFragment extends Fragment {
    private View view;
    private Context context;

    private TextView tv_menuHeader;
    private ImageView iv_Search, iv_AddSubject, iv_Back, iv_Close;
    private TextInputEditText et_SearchSubject;
    private SwipeRefreshLayout swipe_SubjectList;
    private ConstraintLayout indicator_empty_record;
    private RecyclerView rv_SubjectList;

    private CardView cardview;
    private String usertype;

    public MySubjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_subjects, container, false);
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
        tv_menuHeader = view.findViewById(R.id.tv_menuHeader);
        iv_Search = view.findViewById(R.id.iv_Search);
        iv_AddSubject = view.findViewById(R.id.iv_AddSubject);
        iv_Back = view.findViewById(R.id.iv_Back);
        iv_Close = view.findViewById(R.id.iv_Close);
        et_SearchSubject = view.findViewById(R.id.et_SearchSubject);
        swipe_SubjectList = view.findViewById(R.id.swipe_SubjectList);
        rv_SubjectList = view.findViewById(R.id.rv_SubjectList);
        cardview = view.findViewById(R.id.cardview);
        et_SearchSubject.requestFocus();
        iv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_menuHeader.setVisibility(View.GONE);
                iv_Search.setVisibility(View.GONE);
                iv_AddSubject.setVisibility(View.GONE);
                et_SearchSubject.setVisibility(View.VISIBLE);
                iv_Back.setVisibility(View.VISIBLE);
                iv_Close.setVisibility(View.VISIBLE);
            }
        });
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_menuHeader.setVisibility(View.VISIBLE);
                iv_Search.setVisibility(View.VISIBLE);
                iv_AddSubject.setVisibility(View.VISIBLE);
                et_SearchSubject.setVisibility(View.GONE);
                iv_Back.setVisibility(View.GONE);
                iv_Close.setVisibility(View.GONE);
            }
        });
        iv_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SearchSubject.setText("");
            }
        });
        iv_AddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, AddEducatorActivity.class);
//                intent.putExtra("USERTYPE", "educator");
//                startActivity(intent);
                openAddEditDialog();
            }
        });
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ManageSubjectActivity.class);
                intent.putExtra("USERTYPE", usertype);
                startActivity(intent);
            }
        });
    }

    private void openAddEditDialog(){
        Dialog dialog=new Dialog(context,android.R.style.Theme_Light_NoTitleBar);
        dialog.setContentView(R.layout.dialog_add_update_subject);
        final ImageView iv_Back, iv_AddEdit,iv_Delete,iv_SubjectImage;
        final Button btn_ChooseFile,btn_UploadPhoto;
        final TextView tv_FileChose;
        final RecyclerView rv_Search;
        final SwipeRefreshLayout swipeRefreshLayout;
        final ConstraintLayout emptyIndicator;

        iv_Back = dialog.findViewById(R.id.iv_Back);
        iv_AddEdit = dialog.findViewById(R.id.iv_AddEdit);
        iv_Delete = dialog.findViewById(R.id.iv_Delete);
        iv_SubjectImage = dialog.findViewById(R.id.iv_SubjectImage);
        btn_ChooseFile = dialog.findViewById(R.id.btn_ChooseFile);
        btn_UploadPhoto = dialog.findViewById(R.id.btn_UploadPhoto);
        tv_FileChose = dialog.findViewById(R.id.tv_FileChose);
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
