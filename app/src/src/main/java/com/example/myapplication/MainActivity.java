package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static java.util.logging.Level.INFO;

public class MainActivity extends AppCompatActivity {

    //submit buttion
    private Button submitButton = null;
    //post singin text
    private TextView postSingedText = null;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static String Userid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton =  (Button) findViewById(R.id.sing_button);
        // onclick action for sing in button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // singed completion modification
                //postSingedText = (TextView) findViewById(R.id.postSignin);
                //postSingedText.setText("Sing IN completed");
                final EditText useridText = (EditText) findViewById(R.id.username);
                final EditText userPasswd = (EditText) findViewById(R.id.passwd);
                Userid = useridText.getText().toString();

                db.collection("users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    boolean flag = true;                      //To mention user sing in end of all users list
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(String.valueOf(INFO), document.getId() + " => " + document.getData());
                                        if (document.getId().equals(String.valueOf(useridText.getText()))) {
                                            String username = document.getData().get("uname").toString();
                                            String upasswd = document.getData().get("passwd").toString();
                                            // username and password verified from db
                                            if (username.equalsIgnoreCase(String.valueOf(useridText.getText()))) {
                                                Log.d(String.valueOf(INFO), "User name verified ");
                                                if (upasswd.equalsIgnoreCase(String.valueOf(userPasswd.getText()))) {
                                                    Log.d(String.valueOf(INFO), "User Password verified ");
                                                    flag = false;
                                                    Toast.makeText(getApplicationContext(),"Sing IN completed",Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Log.d(String.valueOf(INFO), "Wrong password");
                                                    continue;
                                                }
                                                //check in shopowner database for any available shopid
                                                db.collection("/shopowner/"+useridText.getText()+"/Stores")
                                                        .get()
                                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                if (task.isSuccessful()) {
                                                                    // not empty means owner as considered
                                                                    if(!(task.getResult().isEmpty())) {
                                                                        Log.d(String.valueOf(INFO), "User type is Shop-Owner from ShopOwner Database");
                                                                        Toast.makeText(getApplicationContext(),"User is Shop-Owner",Toast.LENGTH_SHORT).show();
                                                                        Intent activity2Intent = new Intent(getApplicationContext(), Main2Activity.class);
                                                                        startActivity(activity2Intent);
                                                                    }else {
                                                                        // shopper becasue of empty or null set of data
                                                                        Log.d(String.valueOf(INFO), "User type is Shopper from ShopOwner Database");
                                                                        Toast.makeText(getApplicationContext(),"User is Shopper",Toast.LENGTH_SHORT).show();
                                                                    }
                                                                    Log.d(String.valueOf(INFO), "Accessed shopowner DB");
                                                                    Log.d(String.valueOf(INFO), "Size is "+String.valueOf(task.getResult().size()));
                                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                                        Log.d(String.valueOf(INFO), document.getId() + " => " + document.getData());
                                                                    }
                                                                } else {
                                                                    Log.d(String.valueOf(INFO), "failed access the shopowner DB");
                                                                    Toast.makeText(getApplicationContext(),"User is Shopper",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                            }
                                            Log.d(String.valueOf(INFO), "Got the user == >" + username);
                                        } else {
                                            //Log.d(String.valueOf(INFO), "User name does not match with "+useridText.getText());
                                        }
                                    }
                                    if (flag) {
                                        Log.d(String.valueOf(INFO), "NO user with == >" + useridText.getText());
                                    }
                                } else {
                                    Log.d(String.valueOf(INFO), "Error getting documents.", task.getException());
                                }
                            }
                        });
            }

        });
    }
}
