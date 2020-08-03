package com.theyestech.yestechpremium.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.theyestech.yestechpremium.R;

public class UserSearchActivity extends AppCompatActivity {
    private View view;
    private Context context;
    private ImageView iv_Back,iv_Close;
    private TextInputEditText et_SearchUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeUI();
    }
    private void initializeUI(){
        et_SearchUser = findViewById(R.id.et_SearchUser);
        et_SearchUser.requestFocus();
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
                et_SearchUser.setText("");
            }
        });
    }
}
