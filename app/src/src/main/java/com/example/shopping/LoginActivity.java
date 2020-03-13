package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText InpuutPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadngBar;
    private TextView AdminLink;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.login_btn);
        InpuutPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        AdminLink =(TextView) findViewById(R.id.admin_panel_link);
        chkBoxRememberMe=findViewById(R.id.Remember_me_chkb);
        loadngBar = new ProgressDialog(this) ;


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login Admin");
                parentDbName ="Admins";
            }
        });
    }

    private void LoginUser() {
        String phone = InpuutPhoneNumber.getText().toString();
        String Password = InputPassword.getText().toString();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "please write your phn num", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "please write your password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadngBar.setTitle("Login Account");
            loadngBar.setMessage("please wait,while we are checking");
            loadngBar.setCanceledOnTouchOutside(false);
            loadngBar.show();

            AllowAccessToAccount(phone,Password);

        }

    }

    private void AllowAccessToAccount(final String phone, final String password) {
        if (chkBoxRememberMe.isChecked()){
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists()){
                    Users userData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if (parentDbName.equals("Admins")){
                        Intent intent= new Intent(LoginActivity.this,AdminCatogiryActivity.class);
                        startActivity(intent);
                    }
                }
                else if (parentDbName.equals("Users")){
                    Intent intent= new Intent(LoginActivity.this,nameActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
