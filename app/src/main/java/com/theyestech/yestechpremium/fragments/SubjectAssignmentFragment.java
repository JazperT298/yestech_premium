package com.theyestech.yestechpremium.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theyestech.yestechpremium.R;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectAssignmentFragment extends Fragment {
    private View view;
    private Context context;

    private FloatingActionButton fab_NewSubjectAssignment;
    private SwipeRefreshLayout swipe_SubjectAssignment;
    private RecyclerView rv_SubjectAssignment;

    private String instructions = "", powerpoint = "", excel = "",word = "", image = "", file = "";
    public SubjectAssignmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_subject_assignment, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
        fab_NewSubjectAssignment = view.findViewById(R.id.fab_NewSubjectAssignment);
        swipe_SubjectAssignment = view.findViewById(R.id.swipe_SubjectAssignment);
        rv_SubjectAssignment = view.findViewById(R.id.rv_SubjectAssignment);
        fab_NewSubjectAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewAssignment();
            }
        });
    }

    private void openAddNewAssignment(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_assignment, null);
        final EditText et_AssignmentInstruction;
        final CheckBox cb_PowerPoint, cb_Excel, cb_Word, cb_ImageVideo;
        final AppCompatButton btn_AddAssignment;

        et_AssignmentInstruction = dialogView.findViewById(R.id.et_AssignmentInstruction);
        cb_PowerPoint = dialogView.findViewById(R.id.cb_PowerPoint);
        cb_Excel = dialogView.findViewById(R.id.cb_Excel);
        cb_Word = dialogView.findViewById(R.id.cb_Word);
        cb_ImageVideo = dialogView.findViewById(R.id.cb_ImageVideo);

        btn_AddAssignment = dialogView.findViewById(R.id.btn_AddAssignment);
        cb_PowerPoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                file = "powerpoint";
            }
        });
        cb_Excel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                file = "excel";
            }
        });
        cb_Word.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                file = "word";
            }
        });
        cb_ImageVideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                file = "iamge/video";
            }
        });
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        btn_AddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructions = et_AssignmentInstruction.getText().toString();

                if (instructions.isEmpty())
                    Toasty.warning(context, "Please input assignment instruction").show();
                else if (file.isEmpty()){
                    Toasty.warning(context, "Please input select file for assignment").show();
                }
                else {
                    Toasty.warning(context, "Wala pay Function. selected " + file).show();
                    b.hide();
                }
            }
        });

        b.show();
        Objects.requireNonNull(b.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
