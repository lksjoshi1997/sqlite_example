package com.example.laxmikant.databaseexample;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Context context;
    ImageView imageView;
    EditText etName, etAge, etCompany, etRole;
    Button btn, showBtn, cameraBtn, galleryBtn;
    DatabaseService dbService;
    String permissions[] = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    int PERMISSION_REQUEST = 100;
    int CAMERA_REQUEST = 150;
    int GALLERY_REQUEST = 160;
    byte[] imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        dbService = new DatabaseService(context);

        init();
        initListener();
        checkPermissions();
    }

    private void init() {
        etName = findViewById(R.id.name);
        etAge = findViewById(R.id.age);
        etCompany = findViewById(R.id.company);
        etRole = findViewById(R.id.role);
        imageView = findViewById(R.id.image);
        btn = findViewById(R.id.saveBtn);
        showBtn = findViewById(R.id.show);
        cameraBtn = findViewById(R.id.cameraBtn);
        galleryBtn = findViewById(R.id.galleryBtn);
    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployeeInfo();
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToShowInformationActivity();
            }
        });
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void navigateToShowInformationActivity() {
        Intent intent = new Intent(context, ShowInformationActivity.class);
        startActivity(intent);
    }

    private void addEmployeeInfo() {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String company = etCompany.getText().toString();
        String role = etRole.getText().toString();
        EmployeeInfoModel infoModel = new EmployeeInfoModel(name, age, company, role, imageData);
        dbService.addInformationToData(infoModel);
    }

    private void checkPermissions() {
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST);
        }
    }

    private boolean hasPermission() {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Thanks Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                checkPermissions();
                Toast.makeText(context, "Permission is required", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void openGallery() {
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, GALLERY_REQUEST);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (data != null && data.getExtras() != null) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(image);
                imageData = convertBitmapImageToByteArray(image);
            }
        }
        if (requestCode == GALLERY_REQUEST) {
            if (data != null) {
                Uri selectedImage = data.getData();
//                imageView.setImageURI(selectedImage);
                try {
                    Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    imageView.setImageBitmap(bitmapImage);
                    imageData = convertBitmapImageToByteArray(bitmapImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] convertBitmapImageToByteArray(Bitmap bitmapImage) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG,0, outputStream);
        return outputStream.toByteArray();
    }
}
