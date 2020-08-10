package com.theyestech.yestechpremium.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.theyestech.yestechpremium.R;
import com.theyestech.yestechpremium.activities.StudentSearchActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectStudentsFragment extends Fragment {
    private View view;
    private Context context;

    private LinearLayout linear1;
    public SubjectStudentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_subject_students, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
        linear1 = view.findViewById(R.id.linear1);
        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
