package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.jar.Attributes;

import javax.xml.transform.Result;

public class AdminAddNewProductActivity extends AppCompatActivity {
    private String CatogiryName,Description, Price,Pname;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName,InputProductDescription,InputProductPrice;
    private static final int GallaryPic=1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImageRef;
    private ProgressDialog loadngBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        CatogiryName = getIntent().getExtras().get("catogiry").toString();
        ProductImageRef= FirebaseStorage.getInstance().getReference().child("Product_Images");

        AddNewProductButton =(Button) findViewById(R.id.add_new_product);
        InputProductImage =(ImageView) findViewById(R.id.select_Product_images);
        InputProductName =(EditText) findViewById(R.id.product_name);
        InputProductDescription =(EditText) findViewById(R.id.product_descrption);
        InputProductPrice=(EditText) findViewById(R.id.product_price);
        loadngBar =new ProgressDialog(this);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
    }

    private void ValidateProductData() {
    }

    private void OpenGallery() {
        Intent gallaryIntent =new Intent();
        gallaryIntent.setAction(Intent.ACTION_GET_CONTENT);
        gallaryIntent.setType("image/*");
        startActivityForResult(gallaryIntent,GallaryPic);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GallaryPic && resultCode== RESULT_OK && data!=null){
            ImageUri=data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }
    private void ValidateProductDate(){
        Description =InputProductDescription.getText().toString();
        Price =InputProductPrice.getText().toString();
        Pname =InputProductName.getText().toString();
         if (ImageUri== null){
             Toast.makeText(this,"product mg s mandatory.", Toast.LENGTH_SHORT).show();
         }
         else if(TextUtils.isEmpty(Description)){
             Toast.makeText(this,"Please write product descripton",Toast.LENGTH_SHORT).show();
         }
         else if(TextUtils.isEmpty(Price)){
             Toast.makeText(this,"Please write product Prce",Toast.LENGTH_SHORT).show();
         }
         else if(TextUtils.isEmpty(Pname)){
             Toast.makeText(this,"Please write product name",Toast.LENGTH_SHORT).show();
         }
         else{
             StoreProductInformaton();
         }
         final StorageReference filefath =ProductImageRef.child(ImageUri.getLastPathSegment() + productRandomKey);
         final UploadTask uploadTask =filefath.putFile(ImageUri);
        Task<Uri>uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }
                downloadImageUrl =filefath.getDownloadUrl().toString();
                return filefath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
             if(task.isSuccessful()) {

                 Toast.makeText(AdminAddNewProductActivity.this,"succesful", Toast.LENGTH_SHORT).show();
                 Intent intent= new Intent(AdminAddNewProductActivity.this,AdminCatogiryActivity.class);
                 startActivity(intent);
                 loadngBar.dismiss();
             }
            }
        });
    }

    private void StoreProductInformaton() {
        loadngBar.setTitle("Add New Product");
        loadngBar.setMessage("please wait,while we Adding product");
        loadngBar.setCanceledOnTouchOutside(false);
        loadngBar.show();
    }
}
