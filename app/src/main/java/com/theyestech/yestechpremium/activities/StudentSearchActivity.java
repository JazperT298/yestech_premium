package com.theyestech.yestechpremium.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.theyestech.yestechpremium.R;

public class StudentSearchActivity extends AppCompatActivity {
    private View view;
    private Context context;
    private ImageView iv_Back,iv_Close;
    private TextInputEditText et_SearchStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeUI();
    }
    private void initializeUI(){
        et_SearchStudent = findViewById(R.id.et_SearchStudent);
        et_SearchStudent.requestFocus();
        iv_Back = findViewById(R.id.iv_Back);
        iv_Close = findViewById(R.id.iv_Close);
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SearchStudent.setText("");
            }
        });
    }
}
