package com.theyestech.yestechpremium.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theyestech.yestechpremium.MainActivity;
import com.theyestech.yestechpremium.R;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    private View view;
    private Context context;

    private EditText et_LoginEmail,et_LoginPassword;
    private FloatingActionButton fab_LoginSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
        et_LoginEmail = findViewById(R.id.et_LoginEmail);
        et_LoginPassword = findViewById(R.id.et_LoginPassword);
        fab_LoginSignIn = findViewById(R.id.fab_LoginSignIn);
        fab_LoginSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_LoginEmail.getText().toString().isEmpty()){
                    Toasty.warning(context,"Please input all fields").show();
                }else {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("USERTYPE", et_LoginEmail.getText().toString());
                    startActivity(intent);
                }
//                if(et_LoginEmail.getText().toString().equals("admin")){
//                    Intent intent = new Intent(context, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else if(et_LoginEmail.getText().toString().equals("educator")){
//
//                }else if(et_LoginEmail.getText().toString().equals("student")){
//
//                }else{
//                    Toasty.warning(context, "Invalid Input!").show();
//                }
            }
        });
    }
}
