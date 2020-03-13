package com.example.shopping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCatogiryActivity extends AppCompatActivity {
    private ImageView glasses, purces_bags, hats, shoes;
    private ImageView mobile_phones, headphones, laptop_pc, watches;
    private ImageView tshirts, sportTshirts, femaleDresses, sweaters;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_catogiry);
        glasses=(ImageView)findViewById(R.id.glasses);
        watches=(ImageView)findViewById(R.id.watches);
        laptop_pc=(ImageView)findViewById(R.id.laptop_pc);
        tshirts=(ImageView)findViewById(R.id.t_shirts);
        sportTshirts=(ImageView)findViewById(R.id.sports_t_shirts);
        sweaters=(ImageView)findViewById(R.id.sweaters);
        shoes=(ImageView)findViewById(R.id.shoes);
        hats=(ImageView)findViewById(R.id.hats);
        headphones=(ImageView)findViewById(R.id.headphones);
        femaleDresses=(ImageView)findViewById(R.id.female_dresses);
        purces_bags=(ImageView)findViewById(R.id.purces_bags);
        mobile_phones=(ImageView)findViewById(R.id.moble_phones);

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "glasses");
                startActivity(intent);
            }
        });
        purces_bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "purces_bags");
                startActivity(intent);

            }
        });
        hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "hats");
                startActivity(intent);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "shoes");
                startActivity(intent);
            }
        });
        mobile_phones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "mobile_phones");
                startActivity(intent);
            }
        });
        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "headphones");
                startActivity(intent);
            }
        });
        laptop_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "laptop_pc");
                startActivity(intent);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "watches");
                startActivity(intent);
            }
        });
        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "tshirts");
                startActivity(intent);
            }
        });
        sportTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "sportTshirts");
                startActivity(intent);
            }
        });
        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "femaleDresses");
                startActivity(intent);
            }
        });
        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatogiryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("catogiry", "sweaters");
                startActivity(intent);
            }
        });
    }
}