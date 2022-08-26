package com.example.secondhand.otherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.MainActivity;
import com.example.secondhand.otherActivities.User;
import com.example.secondhand.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_SignUp extends AppCompatActivity {
    TextView register;
    TextView textLog_Sign, email, password;
    Button btnLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);


        FirebaseAuth database = FirebaseAuth.getInstance();
        register = findViewById(R.id.register);
        textLog_Sign = findViewById(R.id.log_sign);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        btnLog = findViewById(R.id.login);

        User newUser = new User(email.getText().toString(), password.getText().toString());

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnLog.getText().toString().trim() == "Register") {
                    //nay la dang ky
                    database.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login_SignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Login_SignUp.class));
                            } else {
                                Toast.makeText(Login_SignUp.this, "Error register! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else if (btnLog.getText().toString().equals("Log in") ) {
                    //nay la dang nhap
                    if(email.getText().equals(null) || password.getText().equals(null)) {
                        Toast.makeText(Login_SignUp.this, "Your email and password can not be null", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        database.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login_SignUp.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    Toast.makeText(Login_SignUp.this, "Error log in! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
                else {
                    Toast.makeText(Login_SignUp.this, "Error and error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register.getText() == "Log in") {
                    email.setText(null);
                    password.setText(null);
                    textLog_Sign.setText("Log In Good Market");
                    register.setText("Register");
                    btnLog.setText("Log in");

                } else {
                    email.setText(null);
                    password.setText(null);
                    textLog_Sign.setText("Sign Up Good Market");
                    register.setText("Log in");
                    btnLog.setText("Register");

                }
            }
        });

    }
}
