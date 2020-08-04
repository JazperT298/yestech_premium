package com.theyestech.yestechpremium;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private Context context;

    private String role;

    private BottomNavigationView navView,nav_view_educator;
    private String usertype;
    private NavController navController;

    boolean isNavigationHide = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        usertype = intent.getStringExtra("USERTYPE");
        if(usertype.equals("admin")){
            setContentView(R.layout.activity_main);
            navView = findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);
        }else if (usertype.equals("educator")){
            setContentView(R.layout.activity_educator_main);
            nav_view_educator = findViewById(R.id.nav_view_educator);
            NavController nav_host_fragment_educator = Navigation.findNavController(this, R.id.nav_host_fragment_educator);
            //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(nav_view_educator, nav_host_fragment_educator);
        }

        context = this;

    }


}
