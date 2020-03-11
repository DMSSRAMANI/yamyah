package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //submit buttion
    private Button submitButton = null;
    //post singin text
    private TextView postSingedText = null;

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
                postSingedText = (TextView) findViewById(R.id.postSignin);
                postSingedText.setText("Sing IN completed");
            }
        });
    }
}
