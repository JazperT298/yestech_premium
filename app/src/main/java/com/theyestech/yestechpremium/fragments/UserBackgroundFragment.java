package com.theyestech.yestechpremium.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theyestech.yestechpremium.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserBackgroundFragment extends Fragment {

    public UserBackgroundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_background, container, false);
    }
}
