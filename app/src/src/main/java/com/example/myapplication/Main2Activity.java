package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static java.util.logging.Level.INFO;

public class Main2Activity extends Activity {

    //submit buttion
    private Button submitButton = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference rref ;
    static int maxim =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        submitButton =  (Button) findViewById(R.id.addProduct);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText pdctname = (EditText) findViewById(R.id.pname);
                final EditText pdctdesc = (EditText) findViewById(R.id.pdesc);

                final Map<String, Object> product = new HashMap<>();
                product.put("productbyautoid", maxim++);
                product.put("pname", pdctname.getText().toString());
                product.put("pdesc", pdctdesc.getText().toString());
                product.put("shopownerid", MainActivity.Userid);


                DocumentReference newCityRef = db.collection("products").document("productbyautoid"+String.valueOf(maxim++));
                newCityRef.set(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Product Added", Toast.LENGTH_SHORT).show();
                            Intent activity2Intent = new Intent(getApplicationContext(), Main4Activity.class);
                            startActivity(activity2Intent);
                        }
                    }
                });
                Log.d(String.valueOf(INFO), newCityRef.getId() + "Inserted");

            }
        });
    }
}
