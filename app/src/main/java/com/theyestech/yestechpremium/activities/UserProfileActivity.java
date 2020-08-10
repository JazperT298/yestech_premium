package com.theyestech.yestechpremium.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.theyestech.yestechpremium.R;
import com.theyestech.yestechpremium.dialogs.UserPhotoBottomSheetDialog;
import com.theyestech.yestechpremium.utils.GlideOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class UserProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private View view;
    private Context context;

    //Admin
    private Button btn_ChooseFile, btn_UploadPhoto, btn_UpdateInfo, btn_UpdateCredential,btn_UpdateBackground;
    private ImageView iv_UserSchoolImage,iv_UserProfileBack,iv_UserCoverPhoto,iv_UserProfileBackground,iv_UserProfileImage;
    private TextView tv_FileChose,tv_SchoolName,tv_SchoolAddress,tv_SchoolDetails,tv_SchoolMotto,tv_Username,tv_Password,tv_ConfirmPassword,tv_UserProfileMotto,tv_UserProfileFullname;
    private AppCompatButton btn_Save;
    private TextView tv_UserSchoolName, tv_UserSchoolMotto;

    //User
    private Button btn_UpdateUserInfo, btn_UpdateUserBackground,btn_UpdateUserSocial,btn_UpdateUserCredential;
    private EditText et_Birthday;

    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;

    private static final int VIDEO_PERMISSION_CODE = 2000;
    private static final int VIDEO_REQUEST_CODE = 3000;

    private static final int CAMERA_PERMISSION_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int DOCUMENT_PERMISSION_CODE = 103;
    private static final int DOCUMENT_REQUEST_CODE = 104;

    private String storagePermission[];
    private String cameraPermission[];
    private Uri selectedFile;
    private String selectedFilePath = "";
    private String displayName = "";
    private File myFile;
    private String name = "", address = "", details = "", motto = "", facebook = "", instagram = "", skype = "", twitter = "", email = "", zoom = "", birthday = "";
    private String usertype;

    private BottomSheetDialog bottomSheetDialog;

    private boolean isSelected;
    private int select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        usertype = intent.getStringExtra("USERTYPE");
        if(usertype.equals("admin")){
            setContentView(R.layout.activity_school_profile);
        }else if (usertype.equals("educator")){
            setContentView(R.layout.activity_user_profile);
        }
        context = this;
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(usertype.equals("admin")){
            initializeUI();
        }else if (usertype.equals("educator")){
            initializeUserUI();
        }
    }

    private void initializeUI(){
        tv_UserProfileFullname = findViewById(R.id.tv_UserProfileFullname);
        tv_UserProfileMotto = findViewById(R.id.tv_UserProfileMotto);
        btn_ChooseFile = findViewById(R.id.btn_ChooseFile);
        btn_UploadPhoto = findViewById(R.id.btn_UploadPhoto);
        btn_UpdateInfo = findViewById(R.id.btn_UpdateInfo);
        btn_UpdateCredential = findViewById(R.id.btn_UpdateCredential);
        iv_UserProfileImage = findViewById(R.id.iv_UserProfileImage);
        iv_UserProfileBack = findViewById(R.id.iv_UserProfileBack);
        tv_FileChose = findViewById(R.id.tv_FileChose);
        tv_SchoolName = findViewById(R.id.tv_SchoolName);
        tv_SchoolAddress = findViewById(R.id.tv_SchoolAddress);
        tv_SchoolDetails = findViewById(R.id.tv_SchoolDetails);
        tv_SchoolMotto = findViewById(R.id.tv_SchoolMotto);
        tv_Username = findViewById(R.id.tv_Username);
        tv_Password = findViewById(R.id.tv_Password);
        tv_ConfirmPassword = findViewById(R.id.tv_ConfirmPassword);
        iv_UserProfileBackground = findViewById(R.id.iv_UserProfileBackground);
        tv_UserSchoolName = findViewById(R.id.tv_UserSchoolName);
        tv_UserSchoolMotto = findViewById(R.id.tv_UserSchoolMotto);
        tv_UserSchoolName.setText("Montessori de Oro");
        tv_UserSchoolMotto.setText("YOUR FUTURE, OUR PRIDE");
        btn_ChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = 2;
                openBottomSheetDialog();
            }
        });

        btn_UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayName.isEmpty()){
                    Toasty.warning(context, "Please chose a file").show();
                }else{
                    Toasty.warning(context, "Wala pay function").show();
                    tv_FileChose.setText("No File Chosen");
                    btn_ChooseFile.setVisibility(View.VISIBLE);
                    btn_UploadPhoto.setVisibility(View.GONE);
                }
            }
        });
        iv_UserProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_UpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateInfoDialog();
            }
        });
        btn_UpdateCredential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateCredentials();
            }
        });
    }

    private void initializeUserUI(){
        tv_UserProfileFullname = findViewById(R.id.tv_UserProfileFullname);
        tv_UserProfileMotto = findViewById(R.id.tv_UserProfileMotto);
        btn_ChooseFile = findViewById(R.id.btn_ChooseFile);
        btn_UploadPhoto = findViewById(R.id.btn_UploadPhoto);
        btn_UpdateInfo = findViewById(R.id.btn_UpdateInfo);
        btn_UpdateCredential = findViewById(R.id.btn_UpdateCredential);
        iv_UserProfileImage = findViewById(R.id.iv_UserProfileImage);
        iv_UserProfileBack = findViewById(R.id.iv_UserProfileBack);
        tv_FileChose = findViewById(R.id.tv_FileChose);
        tv_SchoolName = findViewById(R.id.tv_SchoolName);
        tv_SchoolAddress = findViewById(R.id.tv_SchoolAddress);
        tv_SchoolDetails = findViewById(R.id.tv_SchoolDetails);
        tv_SchoolMotto = findViewById(R.id.tv_SchoolMotto);
        tv_Username = findViewById(R.id.tv_Username);
        tv_Password = findViewById(R.id.tv_Password);
        tv_ConfirmPassword = findViewById(R.id.tv_ConfirmPassword);
        iv_UserCoverPhoto = findViewById(R.id.iv_UserCoverPhoto);
        iv_UserProfileBackground = findViewById(R.id.iv_UserProfileBackground);
        btn_Save = findViewById(R.id.btn_Save);
        btn_UpdateUserBackground = findViewById(R.id.btn_UpdateUserBackground);
        btn_UpdateUserInfo = findViewById(R.id.btn_UpdateUserInfo);
        btn_UpdateUserSocial = findViewById(R.id.btn_UpdateUserSocial);
        btn_UpdateUserCredential = findViewById(R.id.btn_UpdateUserCredential);
        tv_UserProfileFullname.setText("Mugot, Rosalind");
        tv_UserProfileMotto.setText("Education is the mother of leadership.");
        iv_UserCoverPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = 1;
                openBottomSheetDialog();
            }
        });
        btn_ChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = 2;
                openBottomSheetDialog();
            }
        });
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.warning(context, "Wala pay function").show();
                iv_UserCoverPhoto.setVisibility(View.VISIBLE);
                btn_Save.setVisibility(View.GONE);
            }
        });
        btn_UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayName.isEmpty()){
                    Toasty.warning(context, "Please chose a file").show();
                }else{
                    Toasty.warning(context, "Wala pay function").show();
                    tv_FileChose.setText("No File Chosen");
                    btn_ChooseFile.setVisibility(View.VISIBLE);
                    btn_UploadPhoto.setVisibility(View.GONE);
                }
            }
        });
        iv_UserProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_UpdateUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateUserInfoDialog();
            }
        });
        btn_UpdateUserBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateUserBackgroundDialog();
            }
        });
        btn_UpdateUserSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateUserSocialDialog();
            }
        });
        btn_UpdateUserCredential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateUserCredentials();
            }
        });

    }

    private void openBottomSheetDialog(){

        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_chose_photo, null);

        ConstraintLayout constraint1 = view.findViewById(R.id.constraint1);
        ConstraintLayout constraint2 = view.findViewById(R.id.constraint2);
        constraint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
                bottomSheetDialog.dismiss();
            }
        });
        constraint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkStoragePermission()) {
                    requestStoragePermission();
                } else {
                    selectedFilePath = "";
                    pickImageGallery();
                }
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);

        bottomSheetDialog.show();
    }

    private void openUpdateInfoDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_update_info, null);
        final EditText et_SchoolName, et_SchoolAddress, et_SchoolDetails, et_SchoolMotto;
        final ImageView iv_Save;

        et_SchoolName = dialogView.findViewById(R.id.et_SchoolName);
        et_SchoolAddress = dialogView.findViewById(R.id.et_SchoolAddress);
        et_SchoolDetails = dialogView.findViewById(R.id.et_SchoolDetails);
        et_SchoolMotto = dialogView.findViewById(R.id.et_SchoolMotto);

        iv_Save = dialogView.findViewById(R.id.iv_Save);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        iv_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_SchoolName.getText().toString();
                address = et_SchoolAddress.getText().toString();
                details = et_SchoolDetails.getText().toString();
                motto = et_SchoolMotto.getText().toString();

                if (name.isEmpty() || address.isEmpty() || details.isEmpty() || motto.isEmpty())
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

    private void openUpdateCredentials(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_update_credentials, null);
        final EditText et_Username, et_Password, et_ConfirmPassword;
        final AppCompatButton btn_Save;

        et_Username = dialogView.findViewById(R.id.et_Username);
        et_Password = dialogView.findViewById(R.id.et_Password);
        et_ConfirmPassword = dialogView.findViewById(R.id.et_ConfirmPassword);

        btn_Save = dialogView.findViewById(R.id.btn_Save);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_Username.getText().toString();
                address = et_Password.getText().toString();
                details = et_ConfirmPassword.getText().toString();

                if (name.isEmpty() || address.isEmpty() || details.isEmpty())
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

    private void openUpdateUserInfoDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_update_user_info, null);
        final EditText et_Firstname, et_Lastname, et_Middlename,et_Address,et_Email;
        final AppCompatButton btn_Save;

        et_Firstname = dialogView.findViewById(R.id.et_Firstname);
        et_Lastname = dialogView.findViewById(R.id.et_Lastname);
        et_Middlename = dialogView.findViewById(R.id.et_Middlename);
        et_Address = dialogView.findViewById(R.id.et_Address);
        et_Email = dialogView.findViewById(R.id.et_Email);
        et_Birthday = dialogView.findViewById(R.id.et_Birthday);
        et_Birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btn_Save = dialogView.findViewById(R.id.btn_Save);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebook = et_Firstname.getText().toString();
                instagram = et_Lastname.getText().toString();
                skype = et_Middlename.getText().toString();
                twitter = et_Address.getText().toString();
                email = et_Email.getText().toString();
                zoom = et_Birthday.getText().toString();

                if (facebook.isEmpty() || instagram.isEmpty() || skype.isEmpty() || twitter.isEmpty()|| email.isEmpty() || zoom.isEmpty())
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
    private void openUpdateUserBackgroundDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_update_user_background, null);
        final EditText et_EducationAttainment, et_Position, et_SubjectMajor,et_Motto;
        final AppCompatButton btn_Save;

        et_EducationAttainment = dialogView.findViewById(R.id.et_EducationAttainment);
        et_Position = dialogView.findViewById(R.id.et_Position);
        et_SubjectMajor = dialogView.findViewById(R.id.et_SubjectMajor);
        et_Motto = dialogView.findViewById(R.id.et_Motto);

        btn_Save = dialogView.findViewById(R.id.btn_Save);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebook = et_EducationAttainment.getText().toString();
                instagram = et_Position.getText().toString();
                skype = et_SubjectMajor.getText().toString();
                twitter = et_Motto.getText().toString();

                if (facebook.isEmpty() || instagram.isEmpty() || skype.isEmpty() || twitter.isEmpty())
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
    private void openUpdateUserSocialDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_update_user_social, null);
        final EditText et_Facebook, et_Instagram, et_Skype,et_Twitter,et_Email,et_Zoom;
        final AppCompatButton btn_Save;

        et_Facebook = dialogView.findViewById(R.id.et_Facebook);
        et_Instagram = dialogView.findViewById(R.id.et_Instagram);
        et_Skype = dialogView.findViewById(R.id.et_Skype);
        et_Twitter = dialogView.findViewById(R.id.et_Twitter);
        et_Email = dialogView.findViewById(R.id.et_Email);
        et_Zoom = dialogView.findViewById(R.id.et_Zoom);

        btn_Save = dialogView.findViewById(R.id.btn_Save);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebook = et_Facebook.getText().toString();
                instagram = et_Instagram.getText().toString();
                skype = et_Skype.getText().toString();
                twitter = et_Twitter.getText().toString();
                email = et_Email.getText().toString();
                zoom = et_Zoom.getText().toString();

                if (facebook.isEmpty() || instagram.isEmpty() || skype.isEmpty() || twitter.isEmpty() || email.isEmpty() || zoom.isEmpty())
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
    private void openUpdateUserCredentials(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_update_user_credentials, null);
        final EditText et_Username, et_Password, et_ConfirmPassword;
        final ImageView iv_Save;
        final AppCompatButton btn_Save;

        et_Username = dialogView.findViewById(R.id.et_Username);
        et_Password = dialogView.findViewById(R.id.et_Password);
        et_ConfirmPassword = dialogView.findViewById(R.id.et_ConfirmPassword);

        btn_Save = dialogView.findViewById(R.id.btn_Save);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_Username.getText().toString();
                address = et_Password.getText().toString();
                details = et_ConfirmPassword.getText().toString();

                if (name.isEmpty() || address.isEmpty() || details.isEmpty())
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
        et_Birthday.setText(date);
    }

    private void selectActions() {
        String[] items = {" Camera ", " Gallery "};
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Choose File");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    askCameraPermissions();
                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        selectedFilePath = "";
                        pickImageGallery();
                    }
                }
            }
        });
        dialog.create().show();
    }
    private void askCameraPermissions(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }else  {
            pickCamera();
        }
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickImageGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickCamera(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture,  CAMERA_REQUEST_CODE);//
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickImageGallery();
                    } else {
                        Toasty.error(context, "Permission denied ", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length < 0) {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickCamera();
                    } else {
                        Toasty.error(context, "Permission denied ", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                selectedFile = data.getData();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = context.getContentResolver().query(selectedFile,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                selectedFilePath = cursor.getString(columnIndex);
                myFile = new File(selectedFilePath);
                displayName = myFile.getName();
                if(select == 2){
                    Glide.with(context)
                            .load(selectedFile)
                            .apply(GlideOptions.getOptions())
                            .into(iv_UserProfileImage);
                    tv_FileChose.setText(displayName);
                    btn_ChooseFile.setVisibility(View.GONE);
                    btn_UploadPhoto.setVisibility(View.VISIBLE);
                }else{
                    Glide.with(context)
                            .load(selectedFile)
                            .into(iv_UserProfileBackground);
                    iv_UserCoverPhoto.setVisibility(View.GONE);
                    btn_Save.setVisibility(View.VISIBLE);
                }
            }else if (requestCode == CAMERA_REQUEST_CODE) {
                Bitmap image = (Bitmap) data.getExtras().get("data");

                Random r = new Random();
                int randomNumber = r.nextInt(10000);
                selectedFilePath = String.valueOf(randomNumber);
                File filesDir = getApplicationContext().getFilesDir();
                myFile = new File(filesDir, selectedFilePath + ".jpg");
                displayName = myFile.getName();
                //iv_UserProfileImage.setImageBitmap(image);
                if(select == 2){
                    Glide.with(context)
                            .load(image)
                            .apply(GlideOptions.getOptions())
                            .into(iv_UserProfileImage);
                    tv_FileChose.setText(displayName);
                    btn_ChooseFile.setVisibility(View.GONE);
                    btn_UploadPhoto.setVisibility(View.VISIBLE);
                }else{
                    Glide.with(context)
                            .load(selectedFile)
                            .into(iv_UserProfileBackground);
                    iv_UserCoverPhoto.setVisibility(View.GONE);
                    btn_Save.setVisibility(View.VISIBLE);
                }
                OutputStream os;
                try {
                    os = new FileOutputStream(myFile);
                    image.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }
            }
        }
    }

}
