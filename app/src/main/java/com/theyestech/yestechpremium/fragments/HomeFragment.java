package com.theyestech.yestechpremium.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.theyestech.yestechpremium.R;
import com.theyestech.yestechpremium.activities.UserProfileActivity;
import com.theyestech.yestechpremium.activities.UserSearchActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    private Context context;

    private String role,password;

    private ImageView iv_HomeProfile;
    private LinearLayout linear1;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeAdminUI();
    }

    private void initializeAdminUI(){
        iv_HomeProfile = view.findViewById(R.id.iv_HomeProfile);
        linear1 = view.findViewById(R.id.linear1);
        iv_HomeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserProfileActivity.class);
                startActivity(intent);
            }
        });
        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
