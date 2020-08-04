package com.theyestech.yestechpremium.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.theyestech.yestechpremium.R;

public class AddEducatorActivity extends AppCompatActivity {
    private Context context;

    private String role;

    private BottomNavigationView navView;
    private ImageView iv_UserProfileBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_educator);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_announcement, R.id.navigation_video_lib, R.id.navigation_educator_list, R.id.navigation_student_list)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
        iv_UserProfileBack = findViewById(R.id.iv_UserProfileBack);
        iv_UserProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
