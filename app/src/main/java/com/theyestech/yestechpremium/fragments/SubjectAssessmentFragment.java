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
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.theyestech.yestechpremium.R;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectAssessmentFragment extends Fragment {
    private View view;
    private Context context;

    private FloatingActionButton fab_QuizAssessment;
    private SwipeRefreshLayout swipe_QuizAssessment;
    private RecyclerView rv_QuizAssessment;

    private ArrayList<String> sStudent = new ArrayList<>();
    private ArrayList<String> sQuiz = new ArrayList<>();

    private String student = "", quiz = "";
    public SubjectAssessmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_subject_assessment, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeUI();

        sStudent.add("--All Student--");
        sStudent.add("Dave Mustaine");
        sStudent.add("James Hitfield");
        sStudent.add("Marty Fredman");


        sQuiz.add("--All Quiz--");
        sQuiz.add("Quiz 1");
        sQuiz.add("Quiz 2");
        sQuiz.add("Quiz 3");
    }

    private void initializeUI(){
        fab_QuizAssessment = view.findViewById(R.id.fab_QuizAssessment);
        swipe_QuizAssessment = view.findViewById(R.id.swipe_QuizAssessment);
        rv_QuizAssessment = view.findViewById(R.id.rv_QuizAssessment);
        fab_QuizAssessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewAssessment();
            }
        });
    }

    private void openViewAssessment(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_view_assessment, null);
        final AppCompatButton btn_Assess;
        final MaterialSpinner sp_SelectStudent,sp_SelectQuiz;

        btn_Assess = dialogView.findViewById(R.id.btn_Assess);
        sp_SelectStudent = dialogView.findViewById(R.id.sp_SelectStudent);
        sp_SelectQuiz = dialogView.findViewById(R.id.sp_SelectQuiz);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        student = sStudent.get(0);

        sp_SelectStudent.setItems(sStudent);
        sp_SelectStudent.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                student = sStudent.get(position);
            }
        });
        quiz = sQuiz.get(0);

        sp_SelectQuiz.setItems(sQuiz);
        sp_SelectQuiz.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                quiz = sQuiz.get(position);
            }
        });
        btn_Assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (student.isEmpty() || quiz.isEmpty()) {
                    Toasty.warning(context, "Please select").show();
                }
                else {
                    Toasty.warning(context, "Wala pay Function").show();
                    b.hide();
                }
            }
        });

        b.show();
        Objects.requireNonNull(b.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
