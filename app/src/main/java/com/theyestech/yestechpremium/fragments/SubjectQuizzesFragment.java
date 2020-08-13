package com.theyestech.yestechpremium.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.theyestech.yestechpremium.R;

import java.util.Calendar;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectQuizzesFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    private View view;
    private Context context;

    private ImageView iv_AddQuiz, iv_SearchQuiz;
    private TextInputEditText et_SearchQuiz;
    private EditText et_QuizDeadine;

    private SwipeRefreshLayout swipe_Quizzes;
    private RecyclerView rv_Quizzes;

    private String title = "", instructions = "", items = "", deadline = "";
    public SubjectQuizzesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_subject_quizzes, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
        iv_AddQuiz = view.findViewById(R.id.iv_AddQuiz);
        iv_SearchQuiz = view.findViewById(R.id.iv_SearchQuiz);
        et_SearchQuiz = view.findViewById(R.id.et_SearchQuiz);
        swipe_Quizzes = view.findViewById(R.id.swipe_Quizzes);
        rv_Quizzes = view.findViewById(R.id.rv_Quizzes);

        iv_AddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddQuizDialog();
            }
        });
    }

    private void openAddQuizDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_quiz, null);
        final EditText et_QuizTitle, et_Instruction, et_QuizItems;
        final AppCompatButton btn_AddQuiz;

        et_QuizTitle = dialogView.findViewById(R.id.et_QuizTitle);
        et_Instruction = dialogView.findViewById(R.id.et_Instruction);
        et_QuizItems = dialogView.findViewById(R.id.et_QuizItems);
        et_QuizDeadine = dialogView.findViewById(R.id.et_QuizDeadine);
        et_QuizDeadine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btn_AddQuiz = dialogView.findViewById(R.id.btn_AddQuiz);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        btn_AddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_QuizTitle.getText().toString();
                instructions = et_Instruction.getText().toString();
                items = et_QuizItems.getText().toString();
                deadline = et_QuizDeadine.getText().toString();

                if (title.isEmpty() || instructions.isEmpty() || items.isEmpty() || deadline.isEmpty())
                    Toasty.warning(context, "Please input all fields.").show();
                else {
                    Toasty.warning(context, "Wala pay Function.").show();
                    b.hide();
                }
            }
        });

        b.show();
        Objects.requireNonNull(b.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void showDatePickerDialog(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(context, (DatePickerDialog.OnDateSetListener) this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = ""+ month +"/"+ dayOfMonth +"/"+ year;
        et_QuizDeadine.setText(date);
    }
}
