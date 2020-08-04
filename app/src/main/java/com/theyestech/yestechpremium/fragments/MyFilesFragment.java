package com.theyestech.yestechpremium.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.theyestech.yestechpremium.R;
import com.theyestech.yestechpremium.activities.ManageSubjectActivity;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFilesFragment extends Fragment {
    private View view;
    private Context context;

    private TextView tv_menuHeader, tv_Filename;
    private ImageView iv_Search, iv_AddFile, iv_Back, iv_Close;
    private TextInputEditText et_SearchFile;
    private SwipeRefreshLayout swipe_SubjectList;
    private ConstraintLayout indicator_empty_record;
    private RecyclerView rv_SubjectList;

    private String usertype;
    private String title = "";
    private String details = "";
    public MyFilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_files, container, false);
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
        iv_AddFile = view.findViewById(R.id.iv_AddFile);
        iv_Back = view.findViewById(R.id.iv_Back);
        iv_Close = view.findViewById(R.id.iv_Close);
        et_SearchFile = view.findViewById(R.id.et_SearchFile);
        swipe_SubjectList = view.findViewById(R.id.swipe_SubjectList);
        rv_SubjectList = view.findViewById(R.id.rv_SubjectList);
        et_SearchFile.requestFocus();
        iv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_menuHeader.setVisibility(View.GONE);
                iv_Search.setVisibility(View.GONE);
                iv_AddFile.setVisibility(View.GONE);
                et_SearchFile.setVisibility(View.VISIBLE);
                iv_Back.setVisibility(View.VISIBLE);
                iv_Close.setVisibility(View.VISIBLE);
            }
        });
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_menuHeader.setVisibility(View.VISIBLE);
                iv_Search.setVisibility(View.VISIBLE);
                iv_AddFile.setVisibility(View.VISIBLE);
                et_SearchFile.setVisibility(View.GONE);
                iv_Back.setVisibility(View.GONE);
                iv_Close.setVisibility(View.GONE);
            }
        });
        iv_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SearchFile.setText("");
            }
        });
        iv_AddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFileDialog();
            }
        });
    }
    private void openAddFileDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_file, null);
        final EditText et_AddFileTitle, et_AddFileDescription;
        final ImageView iv_File, iv_Save;

        et_AddFileTitle = dialogView.findViewById(R.id.et_AddFileTitle);
        et_AddFileDescription = dialogView.findViewById(R.id.et_AddFileDescription);
        //tvHeader = dialogView.findViewById(R.id.tvHeader);
//        btn_ChooseAddNewsFeedFile = dialogView.findViewById(R.id.btn_ChooseAddNewsFeedFile);
//        btn_AddEditNewsFeedSave = dialogView.findViewById(R.id.btn_AddEditNewsFeedSave);
        iv_File = dialogView.findViewById(R.id.iv_File);
        iv_Save = dialogView.findViewById(R.id.iv_Save);
        tv_Filename = dialogView.findViewById(R.id.tv_Filename);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        iv_File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectActions();
            }
        });

        iv_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_AddFileTitle.getText().toString();
                details = et_AddFileDescription.getText().toString();
                if (title.isEmpty() || details.isEmpty())
                    Toasty.warning(context, "Please input all fields.").show();
                else {
                    Toasty.warning(context, "Wala pay function").show();
                    b.hide();
                }
            }
        });

        b.show();
        Objects.requireNonNull(b.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
