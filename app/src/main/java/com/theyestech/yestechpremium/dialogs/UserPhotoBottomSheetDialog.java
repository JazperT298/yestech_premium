package com.theyestech.yestechpremium.dialogs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.theyestech.yestechpremium.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class UserPhotoBottomSheetDialog  extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_chose_photo, container, false);

        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ConstraintLayout constraint1 = v.findViewById(R.id.constraint1);
        ConstraintLayout constraint2 = v.findViewById(R.id.constraint2);
        constraint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
                dismiss();
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
                dismiss();
            }
        });
        return v;
    }

    private void askCameraPermissions(){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }else  {
            pickCamera();
        }
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(getActivity(), storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
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
                        Toasty.error(getActivity(), "Permission denied ", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length < 0) {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickCamera();
                    } else {
                        Toasty.error(getActivity(), "Permission denied ", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                selectedFile = data.getData();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedFile,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                selectedFilePath = cursor.getString(columnIndex);
                myFile = new File(selectedFilePath);
                displayName = myFile.getName();
                mListener.onButtonClicked(displayName);
                //tv_FileChose.setText(displayName);

            }else if (requestCode == CAMERA_REQUEST_CODE) {
                Bitmap image = (Bitmap) data.getExtras().get("data");

                Random r = new Random();
                int randomNumber = r.nextInt(10000);
                selectedFilePath = String.valueOf(randomNumber);
                File filesDir = getActivity().getFilesDir();
                myFile = new File(filesDir, selectedFilePath + ".jpg");
                displayName = myFile.getName();
                mListener.onButtonClicked(displayName);
                //tv_FileChose.setText(displayName);
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
    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

}
