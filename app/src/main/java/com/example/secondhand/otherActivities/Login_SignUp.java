package com.example.secondhand.otherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.R;

public class Login_SignUp extends AppCompatActivity {
    TextView register;
    TextView textLog_Sign, email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);

        register = findViewById(R.id.register);
        textLog_Sign = findViewById(R.id.log_sign);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register.getText() == "Register") {
                    textLog_Sign.setText("Sign Up Good Market");
                    register.setText("Log in");
                }
                else {
                    textLog_Sign.setText("Log In Good Market");
                    register.setText("Register");
                }
            }
        });


    }
}
