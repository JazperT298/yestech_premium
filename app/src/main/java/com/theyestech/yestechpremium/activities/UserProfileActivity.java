package com.theyestech.yestechpremium.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theyestech.yestechpremium.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class UserProfileActivity extends AppCompatActivity {
    private View view;
    private Context context;

    private Button btn_ChooseFile, btn_UploadPhoto, btn_UpdateInfo, btn_UpdateCredential;
    private ImageView iv_UserProfileImage,iv_UserProfileBack;
    private TextView tv_FileChose,tv_SchoolName,tv_SchoolAddress,tv_SchoolDetails,tv_SchoolMotto,tv_Username,tv_Password,tv_ConfirmPassword;

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
    private String name = "", address = "", details = "", motto = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        context = this;
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
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
        btn_ChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActions();
            }
        });
        btn_UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayName.isEmpty()){
                    Toasty.warning(context, "Please chose a file").show();
                }else{
                    Toasty.warning(context, "Wala pay function").show();
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
        final ImageView iv_Save;

        et_Username = dialogView.findViewById(R.id.et_Username);
        et_Password = dialogView.findViewById(R.id.et_Password);
        et_ConfirmPassword = dialogView.findViewById(R.id.et_ConfirmPassword);

        iv_Save = dialogView.findViewById(R.id.iv_Save);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        iv_Save.setOnClickListener(new View.OnClickListener() {
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
                tv_FileChose.setText(displayName);

            }else if (requestCode == CAMERA_REQUEST_CODE) {
                Bitmap image = (Bitmap) data.getExtras().get("data");

                Random r = new Random();
                int randomNumber = r.nextInt(10000);
                selectedFilePath = String.valueOf(randomNumber);
                File filesDir = getApplicationContext().getFilesDir();
                myFile = new File(filesDir, selectedFilePath + ".jpg");
                displayName = myFile.getName();
                tv_FileChose.setText(displayName);
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
