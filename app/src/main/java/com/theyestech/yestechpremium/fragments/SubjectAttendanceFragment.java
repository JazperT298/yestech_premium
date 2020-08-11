package com.theyestech.yestechpremium.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.theyestech.yestechpremium.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectAttendanceFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    private View view;
    private Context context;
    private TextView textView81;
    private ImageView iv_Calendar, iv_ManualAdd;

    private ArrayList<String> sStudent = new ArrayList<>();

    private String student = "";
    public SubjectAttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_subject_attendance, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeUI();

        sStudent.add("--Select Student--");
        sStudent.add("Dave Mustaine");
        sStudent.add("James Hitfield");
        sStudent.add("Marty Fredman");
    }

    private void initializeUI(){
        textView81 = view.findViewById(R.id.textView81);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        textView81.setText("Students Present on  " + currentDate);
        iv_Calendar = view.findViewById(R.id.iv_Calendar);
        iv_ManualAdd = view.findViewById(R.id.iv_ManualAdd);
        iv_Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        iv_ManualAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManualAddStudent();
            }
        });

        final TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        final ViewPager viewPager = view.findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        int pending = 0;
        viewPagerAdapter.addFragment(new StudentAttendeesFragment(), "Attendees");
        viewPagerAdapter.addFragment(new StudentPendingFragment(), "Pending ("+pending+")" );

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

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
        textView81.setText("Students Present on  " + date);
    }

    private void openManualAddStudent(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_attendance_add_student, null);
        final MaterialSpinner sp_SelectStudent;
        final AppCompatButton btn_Add;
        final ImageView iv_StudentImage;
        final TextView tv_StudentName, tv_StudentEmail, tv_StudentID;

        btn_Add = dialogView.findViewById(R.id.btn_Add);
        sp_SelectStudent = dialogView.findViewById(R.id.sp_SelectStudent);
        iv_StudentImage = dialogView.findViewById(R.id.iv_StudentImage);
        tv_StudentName = dialogView.findViewById(R.id.tv_StudentName);
        tv_StudentEmail = dialogView.findViewById(R.id.tv_StudentEmail);
        tv_StudentID = dialogView.findViewById(R.id.tv_StudentID);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        student = sStudent.get(0);

        sp_SelectStudent.setItems(sStudent);
        sp_SelectStudent.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                student = sStudent.get(position);
                if(student.equals("--Select Student--")){
                    student = "";
                    tv_StudentName.setText("Datu Dacula, Mohammad Yusoph");
                }else{
                    tv_StudentName.setText(student);
                }
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (student.isEmpty())
                    Toasty.warning(context, "Please select a student").show();
                else {
                    Toasty.warning(context, "Wala pay Function.").show();
                    b.hide();
                }
            }
        });

        b.show();
        Objects.requireNonNull(b.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }




    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        // Ctrl + O

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
